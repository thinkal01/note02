package com.note.base.jvm.classloader;

/**
 * 对于静态字段来说，只有直接定义了该字段的类才会被初始化；
 * 当一个类在初始化时，要求其父类全部都已经初始化完毕
 * 由于首次主动使用类时才会被初始化，因此同时打印str和str2时，MyParent1 static block只会打印一次
 * <p>
 * 类加载器并不需要等到某个类被“首次主动使用”时再加载它。
 * 比如调用MyChild1.str2时，此时主动使用的是MyChild1类，该类会被加载。使用-XX:+TraceClassLoading可以发现MyParent1也被加载了
 * <p>
 * -XX:+TraceClassLoading，用于追踪类的加载信息并打印出来
 * JVM option 含义:
 * -XX:+<option>，表示开启option选项
 * -XX:-<option>，表示关闭option选项
 * -XX:<option>=<value>，表示将option选项的值设置为value
 */
public class Test1 {
    public static void main(String[] args) {
        System.out.println(MyChild1.str);
        // System.out.println(MyChild1.str2);
    }
}

class MyParent1 {
    public static String str = "hello world";

    static {
        System.out.println("MyParent1 static block");
    }
}

class MyChild1 extends MyParent1 {
    public static String str2 = "welcome";

    static {
        System.out.println("MyChild1 static block");
    }
}