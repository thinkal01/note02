package com.note.designpattern.bjsxt.prototype;

import java.io.*;
import java.util.Date;

public class Client {
    /**
     * 测试原型模式(浅克隆)
     */
    public void test01() throws Exception {
        Date date = new Date(12312321331L);
        Sheep s1 = new Sheep("少利", date);
        System.out.println(s1);
        System.out.println(s1.getSname());
        System.out.println(s1.getBirthday());

        date.setTime(23432432423L);
        System.out.println(s1.getBirthday());

        Sheep s2 = (Sheep) s1.clone();
        s2.setSname("多利");
        System.out.println(s2);
        System.out.println(s2.getSname());
        System.out.println(s2.getBirthday());
    }

    /**
     * 原型模式(深复制)
     */
    public void test02() throws CloneNotSupportedException {
        Date date = new Date(12312321331L);
        Sheep2 s1 = new Sheep2("少利", date);
        Sheep2 s2 = (Sheep2) s1.clone();   //实现深复制。s2对象的birthday是一个新对象！

        System.out.println(s1);
        System.out.println(s1.getSname());
        System.out.println(s1.getBirthday());

        date.setTime(23432432423L);
        System.out.println(s1.getBirthday());

        s2.setSname("多利");
        System.out.println(s2);
        System.out.println(s2.getSname());
        System.out.println(s2.getBirthday());
    }

    /**
     * 原型模式(深复制,使用序列化和反序列化的方式实现深复制)
     */
    public void test03() throws IOException, ClassNotFoundException {
        Date date = new Date(12312321331L);
        Sheep s1 = new Sheep("少利", date);
        System.out.println(s1);
        System.out.println(s1.getSname());
        System.out.println(s1.getBirthday());

        // 使用序列化和反序列化实现深复制
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(s1);
        byte[] bytes = bos.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);

        Sheep s2 = (Sheep) ois.readObject();   //克隆好的对象！

        System.out.println("修改原型对象的属性值");
        date.setTime(23432432423L);

        System.out.println(s1.getBirthday());

        s2.setSname("多利");
        System.out.println(s2);
        System.out.println(s2.getSname());
        System.out.println(s2.getBirthday());
    }
}
