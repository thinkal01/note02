package com.note.base.jvm.classloader;

/**
 * Created by zhouyilin on 2018/4/19.
 */
public class Test10 {
    static {
        System.out.println("Test10 static block");
    }

    public static void main(String[] args) {
        Parent2 parent2;// 不是对Parent2的主动使用

        System.out.println("------");

        parent2 = new Parent2(); // 对Parent2的主动使用

        System.out.println("------");

        System.out.println(parent2.a);

        System.out.println("------");

        System.out.println(Child2.b); // 对Child2的主动使用
    }

}



class Parent2 {
    static int a = 3;

    static {
        System.out.println("Parent static block");
    }
}

class Child2 extends Parent2 {
    static int b = 4;

    static {
        System.out.println("Child static block");
    }

}
