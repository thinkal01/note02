package com.note.base.io;

import com.note.base.Person;

import java.io.*;

public class ObjectStream01 {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException {
        // 由于我们要对对象进行序列化，所以我们先自定义一个类
        // 序列化数据其实就是把对象写到文本文件
        write();
        read();
    }

    private static void read() throws IOException, ClassNotFoundException {
        // 创建反序列化对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("oos.txt"));
        // 还原对象
        Object obj = ois.readObject();
        // 释放资源
        ois.close();
    }

    private static void write() throws IOException {
        // 创建序列化流对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("oos.txt"));
        Person p = new Person("林青霞", 27);
        oos.writeObject(p);
        // 释放资源
        oos.close();
    }
}