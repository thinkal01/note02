package com.note.base.jvm.classloader;

/**
 * Created by elizhou on 2018/4/20.
 *
 *  sun.misc.Launcher$AppClassLoader@232204a1
    sun.misc.Launcher$ExtClassLoader@14ae5a5
    null
 */
public class Test13 {
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        System.out.println(classLoader); // 打印系统类加载器

        // 系统类加载器的父亲是拓展类加载器，拓展类加载器的父亲是bootstrap加载器
        while (null != classLoader){
            classLoader = classLoader.getParent();

            System.out.println(classLoader);
        }


    }
}
