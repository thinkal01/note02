package com.note.base;

import org.junit.Test;

import java.util.*;

public class Map01 {
    // map键不能重复
    @Test
    public void test04() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "2");
        map.put("status", "1");
        System.out.println(map);
        // Iterator<String> iterator = map.keySet().iterator();
        Iterator<Object> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            iterator.next();
            // 删除map元素
            iterator.remove();
        }

        System.out.println(map);
        // key为null时,值为null
        System.out.println(map.get(null));
    }

    // map排序
    @Test
    public void test06() {
        Map<Integer, Integer> map = new TreeMap<>((o1, o2) -> {
            // 正序,o1小于o2返回-1
            return o1.compareTo(o2);
            // return Integer.compare(o1, o2);
        });
        map.put(1, 1);
        map.put(4, 4);
        map.put(3, 3);
        map.put(2, 2);
    }

    @Test
    public void linkedHashMap() {
        Map<String, String> map = new LinkedHashMap<>();

        map.put("1", "324");
        map.put("12", "324");
        map.put("123", "324");

        // 有序
        System.out.println(map);
    }

    @Test
    public void identityHashMap() {
        Map<String, String> map = new IdentityHashMap<>();

        map.put("1", "324");
        map.put("12", "324");
        map.put("123", "324");

        // 无序
        System.out.println(map);
    }

    @Test
    public void get() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(2, "b");
        map.put(1, "a");
        map.put(4, "d");
        map.put(3, "c");
        // treemap 第一个key
        System.out.println(map.get(map.firstKey()));
        // treemap 最后一个key
        System.out.println(map.get(map.lastKey()));
    }

    /**
     * 获取只读map
     */
    public void unmodifiableMap() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "one");
        map.put(2, "two");
        // 不可修改的map
        map = Collections.unmodifiableMap(map);
    }
}
