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

    // set,add index方法
    public void test05() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        // add(index,object) 原来index位置向后移动
        // list.set(0, "d");

        ListIterator<String> li = list.listIterator();
        while (li.hasNext()) {
            Object obj = li.next();
            if (obj.equals("a"))
                li.set("java");
        }
    }

    // 集合去重
    @Test
    public void test02() {
        ArrayList<String> array = new ArrayList();
        array.add("hello");
        array.add("world");
        array.add("java");
        array.add("world");
        array.add("world");
        array.add("java");

        for (int x = 0; x < array.size() - 1; x++) {
            for (int y = x + 1; y < array.size(); y++) {
                // 依次和后面比较
                if (array.get(x).equals(array.get(y))) {
                    array.remove(y);
                    y--;
                }
            }
        }

        // 方法二
        HashSet<String> hashSet = new HashSet<>(array);
    }

    // 交集
    @Test
    public void test03() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> list2 = new ArrayList<>();
        list2.add(4);
        list2.add(1);
        list2.add(3);

        // 保留交集到list中,list2不变
        // list.retainAll(list2);

        // 差集保存到list中,list2不变
        // list.removeAll(list2);

        // 起始到结束下标,含头不含尾
        List<Integer> subList = list.subList(1, 2);
    }

    @Test
    public void generic() {
        // Collections.emptyList();空集合,不能操作,用作返回空集合
        List<String> list = Collections.emptyList();

        // 不是泛型集合
        List list1 = new ArrayList<String>();
        List<Integer> list2;

        // list2 = list; // error
        list2 = list1;
        list2.add(1);
        setList(list2);
    }

    List<String> strList;

    public void setList(List list) {
        strList = list;
        // 出错,切记方法参数加上泛型 List<String>
        String s = strList.get(0);
    }

    // map键不能重复
    @Test
    public void test04() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "2");
        map.put("status", "1");
    }

    // map排序
    @Test
    public void test06() {
        Map<Integer, Integer> map = new TreeMap<>((o1, o2) -> {
            // 正序
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
