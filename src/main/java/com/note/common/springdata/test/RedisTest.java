package com.note.common.springdata.test;

import com.note.common.springdata.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Redis测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 添加键值对
     */
    @Test
    public void test1() {
        redisTemplate.opsForValue().set("key", "test");
    }

    /**
     * 获取redis中的数据
     */
    @Test
    public void test2() {
        String str = (String) redisTemplate.opsForValue().get("key");
        System.out.println(str);
    }

    /**
     * 添加Users
     */
    @Test
    public void test3() {
        Users users = new Users();
        users.setUserid(1);
        users.setUsername("张三");
        users.setUserage(30);
        //更换序列化器
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.opsForValue().set("users", users);
    }

    /**
     * 获取Users
     */
    @Test
    public void test4() {
        //更换序列化器
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        Users users = (Users) redisTemplate.opsForValue().get("users");
        System.out.println(users);
    }

    /**
     * 添加Users JSON格式
     */
    @Test
    public void test5() {
        Users users = new Users();
        users.setUserid(1);
        users.setUsername("张三");
        users.setUserage(30);

        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Users.class));
        redisTemplate.opsForValue().set("usersjson", users);
    }

    /**
     * 获取Uesrs JSON格式
     */
    @Test
    public void test6() {
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Users.class));
        Users users = (Users) redisTemplate.opsForValue().get("usersjson");
        System.out.println(users);
    }
}
