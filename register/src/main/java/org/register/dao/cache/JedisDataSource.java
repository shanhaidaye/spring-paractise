package org.register.dao.cache;

import redis.clients.jedis.ShardedJedis;

/**
 * Created by shanhaidaye on 2016/8/19.
 */
public interface JedisDataSource {

    public ShardedJedis getRedisClient();

    public void returnResource(ShardedJedis shardedJedis);


    public void returnResource(ShardedJedis shardedJedis, boolean broken);


}
