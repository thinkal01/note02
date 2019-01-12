package com.note.common.mybatis;

import org.apache.ibatis.cache.Cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
​ 自定义缓存对象，该对象必须实现 org.apache.ibatis.cache.Cache
​ 每次查询数据库前，都会先在缓存中查找是否有该缓存对象。
 只有当调用了commit()方法，MyBatis才会往缓存中写入数据，数据记录的键为=数字编号+Mapper名+方法名+SQL语句+参数，值为=返回的对象值。
 */
public class BatisCache implements Cache {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private ConcurrentHashMap<Object, Object> cache = new ConcurrentHashMap<>();
    private String id;

    public BatisCache() {
        System.out.println("初始化-1！");
    }

    //必须有该构造函数
    public BatisCache(String id) {
        System.out.println("初始化id-" + id);
        this.id = id;
    }

    // 获取缓存编号
    @Override
    public String getId() {
        System.out.println("得到ID：" + id);
        return id;
    }

    //获取缓存对象的大小
    @Override
    public int getSize() {
        System.out.println("获取缓存大小！");
        return 0;
    }

    // 保存key值缓存对象
    @Override
    public void putObject(Object key, Object value) {
        System.out.println("往缓存中添加元素：key=" + key + ",value=" + value);
        cache.put(key, value);
    }

    //通过KEY
    @Override
    public Object getObject(Object key) {
        System.out.println("通过kEY获取值：" + key);
        System.out.println("值为：" + cache.get(key));
        return cache.get(key);
    }

    // 通过key删除缓存对象
    @Override
    public Object removeObject(Object key) {
        System.out.println("移除缓存对象：" + key);
        return cache.remove(key);
    }

    // 清空缓存
    @Override
    public void clear() {
        System.out.println("清除缓存！");
        cache.clear();
    }

    // 获取缓存的读写锁
    @Override
    public ReadWriteLock getReadWriteLock() {
        System.out.println("获取锁对象！");
        return lock;
    }

}