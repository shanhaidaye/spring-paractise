package org.seckill.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.register.dao.SeckillDao;
import org.register.dao.cache.RedisClientTemplate;
import org.register.dao.cache.RedisDao;
import org.register.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.util.JedisURIHelper;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by sunshiwang on 16/5/29.
 */
//启动时加载spring IOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring 配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private long seckillId = 10001;
    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private RedisClientTemplate redisClientTemplate;


//    @Autowired
//    private RedisTemplate<String, String> template;
//
//    // inject the template as ListOperations
//    // can also inject as Value, Set, ZSet, and HashOperations
//    @Resource(name="redisTemplate")
//    private ListOperations<String, String> listOps;

//    @Test
//    public void addLink() {
//
//        String key = "testcardkey123";
//        long startCount=0,endCount=0;
//        for (int i = 0; i < 1000; i++) {
//            String card="testcard"+i;
//             listOps.leftPush(key, card);
////            template.boundListOps(key).leftPush(card);
//        }
//        logger.info("push success total:{}",  listOps.size(key));
//    }


    @Test
    public void testGetSeckill() throws Exception {
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null) {
            seckill = seckillDao.queryById(seckillId);
            if (seckill != null) {
                String result = redisDao.putSeckill(seckill);
                logger.info(result);
            }
        }
    }

    @Test
    public void testPutSeckill() throws Exception {
        System.out.println();
    }


    @Test
    public void testPutCards() throws Exception {
        List<String> cardList = new ArrayList();
        for (int i = 1; i < 10; i++) {
            cardList.add((i + 10000 + " card"));
        }
        String key = "zhuxiancard14";
        logger.info(redisDao.setCards(cardList, key) + "");

    }

    @Test
    public void testPutCardsUseTemplate() throws Exception {
        String key = "testcardkey";
        long startCount=0,endCount=0;
        for (int i = 0; i < 10; i++) {
            String card="testcard"+i;
            long result= redisClientTemplate.lpush(key, card);
            if(i==0){
               startCount=result>0?(result-1):0;
            }else if(i==9){
                endCount=result;
            }
        }
        logger.info("push success total:{}",(endCount-startCount)+"");
    }

    @Test
    public void testDelCard() throws Exception {
        String key = "testcardkey";
        long result= redisClientTemplate.delByKey(key);
        logger.info("delete key total:{}",(result)+"");
    }

    @Test
    public void getCardByKeyUseTemplate() throws Exception {

        ExecutorService service = Executors.newFixedThreadPool(20);
        long start=System.currentTimeMillis();
        for (int i = 1; i < 8; i++) {
            Future future = service.submit(new Callable() {
                public String call() throws InterruptedException {
                    boolean ok=true;
                    int count=0;
                    String card = redisClientTemplate.lpop("testcardkey");
                    logger.info(card + "_" + Thread.currentThread().getName());
//                        if(card==null){
//                            ok=false;
//                        }else{
//                            count++;
////                            TimeUnit.MILLISECONDS.sleep(50);
//                        }
//
                    return "数量 "+count + "_" + Thread.currentThread().getName();

                }
            });
            logger.info(future.get().toString());
        }
        logger.info((System.currentTimeMillis() - start) + "");
    }
    @Test
    public void getCardByKey() throws Exception {

        ExecutorService service = Executors.newFixedThreadPool(1000);
        long start=System.currentTimeMillis();
        for (int i = 1; i < 10; i++) {
            Future future = service.submit(new Callable() {
                public String call() throws InterruptedException {
                    boolean ok=true;
                    int count=0;
                        String card = redisDao.getCardByKey("testcardkey");
                        logger.info(card + "_" + Thread.currentThread().getName());
//                        if(card==null){
//                            ok=false;
//                        }else{
//                            count++;
////                            TimeUnit.MILLISECONDS.sleep(50);
//                        }
//
                    return "数量 "+count + "_" + Thread.currentThread().getName();

                }
            });
            logger.info(future.get().toString());
        }
        logger.info((System.currentTimeMillis() - start) + "");
    }

    @Test
    public void getCardByKey1() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 1; i < 5; i++) {
                service.execute(new Runnable() {
                    public void run() {
                        System.out.println("ffff");
                        String card = redisDao.getCardByKey("zhuxiancard12");
                        System.out.println(card + "_" + Thread.currentThread().getName());
                        logger.info(card + "_" + Thread.currentThread().getName());
                    }
                });
        }
        System.out.println("ok");
    }
//
    @Test
    public void test(){
//        String host="redis://:test!%40345678()@127.0.0.1:6379";
//        String host="redis://:web123!%40%23@10.3.247.6:6379";
//        String host="redis://:kTg%25XVOPAZoP1%7e4b@10.131.43.20:6369";
        String host="redis://:kTg%25XVOPAZoP1%7e4b@10.131.43.20:6369";
        URI uri = URI.create(host);
        int port=6379;
        int db=-1;
        String password="";
        System.out.println(uri.getScheme());
        System.out.println(uri.getHost());
        if(JedisURIHelper.isValid(uri)) {
            host = uri.getHost();
             port = uri.getPort();
             password = JedisURIHelper.getPassword(uri);
             db = JedisURIHelper.getDBIndex(uri);
            boolean ssl = uri.getScheme().equals("rediss");
        } else {
             port = 6379;
        }
        logger.info("host={},port={},password={},db={}",host,port,password,db);

        ListOperations<String, String> listOps;
    }


}