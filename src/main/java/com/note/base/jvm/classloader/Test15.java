package com.note.base.jvm.classloader;

/**
 * Created by elizhou on 2018/4/23.
 */
public class Test15 {
    public static void main(String[] args) {
        String[] strings = new String[2];
        System.out.println(strings.getClass().getClassLoader());// 数组类型的classloader=数组里元素所对应的classloader，所以这里输出的是java.lang.string的类加载器bootstrap

        System.out.println("-----");

        Test15[] test15s = new Test15[1];
        System.out.println(test15s.getClass().getClassLoader()); // 同上，这里打印的是Test15的classloader，系统类加载器

        System.out.println("-----");

        int[] ints = new int[1];
        System.out.println(ints.getClass().getClassLoader()); // 原生数据类型的classloader是null，注意这里不是bootstrap加载器
    }
}
