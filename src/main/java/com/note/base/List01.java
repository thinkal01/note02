package com.note.base;

import org.junit.Test;

import java.util.*;

public class List01 {
    // set,add index方法
    @Test
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

    @Test
    public void test04() {
        List<Student01> objects = new ArrayList<Student01>() {
            {
                add(new Student01("test01", 1));
                add(new Student01("test02", 2));
            }
        };
        objects.forEach(System.out::println);

        // 只会复制引用
        // ArrayList<Student01> copyList = new ArrayList<>(objects);
        ArrayList<Student01> copyList = new ArrayList<>();
        // 只会复制引用
        copyList.addAll(objects);
        for (Student01 student01 : copyList) {
            student01.setName("modify");
        }

        objects.forEach(System.out::println);
        copyList.forEach(System.out::println);
    }

    @Test
    public void test06() {
        List<Integer> nums = new ArrayList<>();
        nums.add(3);
        nums.add(5);
        nums.add(1);
        // list 排序
        // Collections.sort(nums);
        nums.sort(Integer::compareTo);
        System.out.println(nums);
    }
}
