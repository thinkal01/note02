package com.note.base;

import org.junit.Test;

public class StringBuffer01 {
    /* 2：StringBuffer和数组的区别?
            二者都可以看出是一个容器，装其他的数据。
            但是呢,StringBuffer的数据最终是一个字符串数据。
            而数组可以放置多种数据，但必须是同一种数据类型的。*/
    @Test
    public void test01() {
        // 指定容量的字符串缓冲区对象
        StringBuffer sb2 = new StringBuffer(50);
        System.out.println("sb2:" + sb2);
        System.out.println("sb2.capacity():" + sb2.capacity());
        System.out.println("sb2.length():" + sb2.length());

        // 指定字符串内容的字符串缓冲区对象
        StringBuffer sb3 = new StringBuffer("hello");
        System.out.println("sb3:" + sb3);
        System.out.println("sb3.capacity():" + sb3.capacity());
        System.out.println("sb3.length():" + sb3.length());

        // 链式编程
        sb2.append("hello").append(true).append(12).append(34.56);
        // 插入到字符串缓冲区里面,并返回字符串缓冲区本身
        sb2.insert(5, "world");

        // 创建对象
        StringBuffer sb = new StringBuffer();

        // 添加功能
        sb.append("hello").append("world").append("java");
        System.out.println("sb:" + sb);

        // 删除指定位置的字符，并返回本身
        sb.deleteCharAt(1);

        // 删除从指定位置开始指定位置结束的内容，并返回本身
        sb.delete(5, 10);
        // 删除所有的数据
        sb.delete(0, sb.length());

        // 从start开始到end用str替换
        sb.replace(5, 10, "节日快乐");

        // 反转
        sb.reverse();

        // 截取功能,返回String
        String s = sb.substring(5);
        String ss = sb.substring(5, 10);
    }

    // 基本方法
    @Test
    public void testStringBuffer() {
        StringBuffer buf = new StringBuffer();
        buf.append("World!!");
        buf.insert(0, "Hello ");        // 在第一个内容之前添加内容
        buf.replace(6, 11, "LiXingHua");        // 将world的内容替换
        buf.delete(6, 15);    // 删除指定范围中的内容
        buf.reverse(); // 反转内容
        if (buf.indexOf("Hello") == -1) {
            System.out.println("没有查找到指定的内容");
        } else {
            System.out.println("查找到指定的内容");
        }
        System.out.println(buf);
        buf.insert(buf.length(), "MLDN~");    // 在最后添加内容
        System.out.println(buf);
    }

}
