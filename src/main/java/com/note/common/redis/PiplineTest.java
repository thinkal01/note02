package com.note.common.redis;

import redis.clients.jedis.*;

import java.util.Arrays;

public class PiplineTest {
    private static int count = 10000;

    public static void main(String[] args) {
        // 使用pipeline的效率要远高于普通的访问方式
        useNormal();
        usePipeline();
    }

    public static void usePipeline() {
        ShardedJedis jedis = getShardedJedis();
        ShardedJedisPipeline pipeline = jedis.pipelined();

        long begin = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            pipeline.set("key_" + i, "value_" + i);
        }
        pipeline.sync();

        jedis.close();
        System.out.println("usePipeline total time:" + (System.currentTimeMillis() - begin));
    }

    public static void useNormal() {
        ShardedJedis jedis = getShardedJedis();

        long begin = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            jedis.set("key_" + i, "value_" + i);
        }

        jedis.close();
        System.out.println("useNormal total time:" + (System.currentTimeMillis() - begin));
    }

    public static ShardedJedis getShardedJedis() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(2);
        poolConfig.setMaxIdle(1);
        poolConfig.setMaxWaitMillis(2000);
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(false);

        JedisShardInfo info1 = new JedisShardInfo("127.0.0.1", 6379);
        JedisShardInfo info2 = new JedisShardInfo("127.0.0.1", 6379);
        ShardedJedisPool pool = new ShardedJedisPool(poolConfig, Arrays.asList(info1, info2));

        return pool.getResource();
    }
}