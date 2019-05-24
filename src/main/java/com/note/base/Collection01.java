package com.note.base;

import org.junit.Test;

import java.util.*;

public class Collection01 {

    // 把集合转成数组
    public void test01() {
        Collection c = new ArrayList();
        c.add("java");
        Object[] objs = c.toArray();
    }

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

}
