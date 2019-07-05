package com.note.designpattern.bjsxt.singleton;

/**
 * 饿汉式单例模式
 */
public class SingletonDemo1 {

    // 类装载时就初始化，浪费内存
    // 基于 classloader 机制避免了多线程同步问题
    private static SingletonDemo1 instance = new SingletonDemo1();

    private SingletonDemo1() {
    }

    //方法没有同步，调用效率高！
    public static SingletonDemo1 getInstance() {
        return instance;
    }

}
