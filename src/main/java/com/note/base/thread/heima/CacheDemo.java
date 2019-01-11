package com.note.base.thread.heima;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {
    private Map<String, Object> cache = new HashMap<>();

    public static void main(String[] args) {
    }

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public Object getData(String key) {
        rwl.readLock().lock();
        Object value;
        try {
            value = cache.get(key);
            if (value == null) {
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    if (value == null) {
                        value = "aaaa";//实际去queryDB();
                    }
                } finally {
                    rwl.writeLock().unlock();
                }
                // 写入锁降级为读取锁
                rwl.readLock().lock();
            }
        } finally {
            rwl.readLock().unlock();
        }
        return value;
    }
}
