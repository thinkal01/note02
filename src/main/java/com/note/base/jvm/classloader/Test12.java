package com.note.base.jvm.classloader;

/**
 * Created by zhouyilin on 2018/4/19.
 *
 * 调用ClassLoader类的loadClass方法加载一个类，并不是对类的主动使用，不会导致类的初始化。
 * Class.forName(String className)只有一个参数的方法，会初始化传入的类名，因此会导致CL类的初始化。
 *  class com.eli.jvm.classloader.CL
    ------
    Class CL
    class com.eli.jvm.classloader.CL

 */
public class Test12 {
    public static void main(String[] args) throws Exception{
        ClassLoader loader = ClassLoader.getSystemClassLoader();

        Class<?> clazz = loader.loadClass("com.eli.jvm.classloader.CL");

        System.out.println(clazz);

        System.out.println("------");

        clazz = Class.forName("com.eli.jvm.classloader.CL");

        System.out.println(clazz);


    }
}

class CL {
    static {
        System.out.println("Class CL");
    }
}