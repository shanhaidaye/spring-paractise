package org.register.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by shanhaidaye on 2016/8/19.
 */
@Repository("jedisDS")
public class JedisDataSourceImp implements JedisDataSource {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ShardedJedisPool shardedJedisPool;

    public ShardedJedis getRedisClient() {

        ShardedJedis sharedJedis = null;
        try {
            sharedJedis = shardedJedisPool.getResource();
        } catch (Exception e) {
            logger.error("[JedisDS] getRedisClent error:" + e.getMessage());
            if (null != sharedJedis) {
                sharedJedis.close();
            }
        }
        return sharedJedis;
    }

    public void returnResource(ShardedJedis shardedJedis) {

    }

    public void returnResource(ShardedJedis shardedJedis, boolean broken) {

    }
}
