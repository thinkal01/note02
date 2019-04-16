package com.note.base.jvm.classloader;

/**
 * Created by zhouyilin on 2018/4/19.
 */
public class Test9 {

    static {
        System.out.println("Test9 static block");
    }

    /**
     *  先输出当前类的静态代码块
     *  调用Child.b是对Child类的主动使用，会初始化其父类。因此打印结果为
     *
     *  Test9 static block
        Parent static block
        Child static block
        4
     */
    public static void main(String[] args) {
        System.out.println(Child.b);


    }

}


class Parent {
    static int a = 3;

    static {
        System.out.println("Parent static block");
    }
}

class Child extends Parent {
    static int b = 4;

    static {
        System.out.println("Child static block");
    }

}

