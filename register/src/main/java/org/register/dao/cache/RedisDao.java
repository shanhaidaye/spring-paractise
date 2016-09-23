package org.register.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.register.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * Created by sunshiwang on 16/5/29.
 */
public class RedisDao {

    public final static int EXRP_HOUR = 60*60;          //一小时
    public final static int EXRP_DAY = 60*60*24;        //一天
    public final static int EXRP_MONTH = 60*60*24*30;   //一个月

    /**
     *控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；如果赋值为-1，则表示不限制；
      如果pool已经分配了maxActive个jedis实例，则此时pool的状态就成exhausted了，在JedisPoolConfig
     */
    public final static int MAX_ACTIVE = -1;

    /**
     * 控制一个pool最多有多少个状态为idle的jedis实例；
     */
    public final static int MAX_IDLE = 60*60*24*30;

    /**
     * 表示当borrow一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
     */
    public final static int MAX_WAIT = 60*60*24*30;

    /**
     * 超時時間
     */
    public final static int TIMEOUT = 3000;

    /**
     * 在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；
     */
    public final static boolean TEST_ON_BORROW = true;
    public final static String AUTH = "web123!@#";


    public Logger logger = LoggerFactory.getLogger(this.getClass());
    private JedisPool jedisPool;



    public RedisDao(String ip, int port) {
//        this.jedisPool = new JedisPool(ip, port);
        initialPool(ip,port);
    }

    private  void initialPool(String ip,int port){
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            this.jedisPool = new JedisPool(config, ip, port, TIMEOUT,AUTH);
        } catch (Exception e) {
            logger.error("First create JedisPool error : "+e);
        }
    }

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public Seckill getSeckill(long seckillId) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckillId;
                //并没有实现内部序列化操作
                //get->byte[]->反序列化 ->Object(Seckill)
                //采用自定义序列化(效率比较 https://github.com/eishay/jvm-serializers/wiki)
                //protostuff:pojo
                byte[] bytes = jedis.get(key.getBytes());
                //缓存重新获取
                if (bytes != null) {
                    //创建空对象
                    Seckill seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    //seckill被反序列化
                    return seckill;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String putSeckill(Seckill seckill) {
        //set Object(Seckill)->序列化->bytes[];
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckill.getSeckillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存
                int timeout = 60 * 60;
                return jedis.setex(key.getBytes(), timeout, bytes);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    public  long  setCards(List<String> cards,String key){
        long i=0;
        Jedis jedis=jedisPool.getResource();
        for(String card:cards){
             i=jedis.lpush(key,card);
            System.out.println(i);
        }
        return 0;
    }
    public  String getCardByKey(String key){
        Jedis jedis=jedisPool.getResource();
      return   jedis.lpop(key);
    }
}
