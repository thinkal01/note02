package com.note.base.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by zhouyilin on 2018/4/26.
 */
public class Test16 extends ClassLoader{

    private String classLoaderName;
    private String path;
    private String fileExtension = ".class";

    public void setPath(String path){
        this.path = path;
    }

    public Test16(String classLoaderName){
        super();
        this.classLoaderName = classLoaderName;
    }

    public Test16(ClassLoader parent, String classLoaderName){
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    public static void main(String[] args) throws Exception {
        Test16 classLoader = new Test16("myLoader"); // 默认的父类加载器是系统类加载器
        classLoader.setPath("/Users/zhouyilin/Desktop/test/");
        // 当加载Test15时，先委托父类加载器加载，父类加载器会继续委托到最顶级Bootstrap加载器，最后会到系统类加载器加载
        // 如果把classpath下的Test15.class文件删掉，父类加载器（系统类加载器）无法在classpath下加载到，此时会由自定义的
        // myLoader类加载器加载
        Class<?> clazz = classLoader.loadClass("com.eli.jvm.classloader.Test15");
        System.out.println("class hascode:" + clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);



        System.out.println("------");
        // 每个类加载器都有自己的命名空间，命名空间由该加载器及所有父加载器所加载的类组成。因此两个类的hascode不一致
        Test16 classLoader2 = new Test16("classLoader2");
        classLoader2.setPath("/Users/zhouyilin/Desktop/test/");
        Class<?> clazz2 = classLoader2.loadClass("com.eli.jvm.classloader.Test15");
        System.out.println("class hascode:" + clazz2.hashCode());
        Object object2 = clazz2.newInstance();
        System.out.println(object2);

    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        System.out.println("findCLass invoke");
        System.out.println("class loader name" + this.classLoaderName);
        byte[] classData = loadClassData(className);

        return this.defineClass(className, classData, 0, classData.length);
    }

    private byte[] loadClassData(String className) {
        InputStream inputStream = null;
        byte[] data = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        className = className.replace(".", "/");

        try {
            inputStream = new FileInputStream(this.path + className + this.fileExtension);
            byteArrayOutputStream = new ByteArrayOutputStream();

            int ch;

            while (-1 != (ch = inputStream.read())){
                byteArrayOutputStream.write(ch);
            }

            data = byteArrayOutputStream.toByteArray();

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                inputStream.close();
                byteArrayOutputStream.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return data;
    }

}
