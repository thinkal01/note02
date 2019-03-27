package com.note.base.jvm.classloader;

/**
 最后输出结果。
 private Singleton counter1:2
 private Singleton counter2:1
 counter1:2
 counter2:0

 由于调用了Singleton类的静态方法，相当于对类进行了主动使用，因此会产生类的加载、连接、初始化等
 主要看两个阶段：连接阶段的准备和初始化

 连接阶段的准备：
 counter1赋默认值0，singleton赋默认值null，counter2默认值0

 初始化阶段：
 从上到下进行初始化。
 counter1初始化成1；
 singleton初始化时调用了new Singleton，因此counter1变成2，counter2变成1，构造方法内打印出
 private Singleton counter1:2
 private Singleton counter2:1

 counter2初始化，又赋值成了0。
 */
public class Test6 {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println("counter1:" + Singleton.counter1);
        System.out.println("counter2:" + Singleton.counter2);
    }
}

class Singleton {
    public static int counter1 = 1;
    public static Singleton singleton = new Singleton();

    private Singleton() {
        counter1++;
        counter2++;
        System.out.println("private Singleton counter1:" + counter1);
        System.out.println("private Singleton counter2:" + counter2);
    }

    public static int counter2 = 0;

    public static Singleton getInstance() {
        return singleton;
    }
}