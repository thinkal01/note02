package com.note.common.redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

public class JedisClient {

    @Test
    public void jedisClient() {
        // Jedis
        Jedis jedis = new Jedis("192.168.161.128", 7001);
        // 通过redis赋值
        jedis.set("s2", "222");
        // 通过redis取值
        String result = jedis.get("s2");
        // 关闭jedis
        jedis.close();
    }

    @Test
    public void jedisPool() {
        // JedisPool
        JedisPool pool = new JedisPool("192.168.161.128", 7001);
        // 通过连接池获取jedis连接对象
        Jedis jedis = pool.getResource();

        jedis.set("s4", "444");
        String result = jedis.get("s3");

        //使用连接时,连接使用完后一定要关闭,关闭后连接会自动回到连接池供别人使用,如果一直不关闭则连接被耗尽之后就会死机
        jedis.close();

        // 关闭连接池
        pool.close();
    }

    @Test
    public void jedisCluster() {
        // 创建jedisCluster
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.161.128", 7001));
        nodes.add(new HostAndPort("192.168.161.128", 7002));
        nodes.add(new HostAndPort("192.168.161.128", 7003));
        nodes.add(new HostAndPort("192.168.161.128", 7004));
        nodes.add(new HostAndPort("192.168.161.128", 7005));
        nodes.add(new HostAndPort("192.168.161.128", 7006));
        // nodes.add(new HostAndPort("192.168.161.128", 7007));

        JedisCluster cluster = new JedisCluster(nodes);

        cluster.set("s4", "444");
        String result = cluster.get("s4");

        cluster.close();
    }
}
