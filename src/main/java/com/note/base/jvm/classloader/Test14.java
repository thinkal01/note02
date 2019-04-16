package com.note.base.jvm.classloader;

import java.net.URL;
import java.util.Enumeration;

/**
 * Created by elizhou on 2018/4/20.
 */
public class Test14 {

    public static void main(String[] args) throws Exception{
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        String resourceName = "com/eli/jvm/classloader/Test13.class";

        Enumeration<URL> urls = classLoader.getResources(resourceName);

        while (urls.hasMoreElements()){
            URL url = urls.nextElement();
            System.out.println(url);
        }

        System.out.println("-----");

        Class strClss = String.class;
        System.out.println(strClss.getClassLoader()); // 输出null。BootStrap加载器

    }
}
