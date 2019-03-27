package com.note.base.jvm.classloader;

/**
 * Created by zhouyilin on 2018/4/19.
 */
public class Test11 {

    /**
     *
     * Child3.a实际上是调用了其父类的静态变量，因此是对Parent3的主动使用，会打印Parent3的静态代码块和a的值3
     * 然后Child3.doSomething也是调用了其父类的静态方法，因此也是对Parent3的主动使用，由于首次主动使用时才会调用静态代码块，因此只会打印doSomething
     *  Parent3 static block
        3
        ------
        do something
     */
    public static void main(String[] args) {
        System.out.println(Child3.a);
        System.out.println("------");
        Child3.doSomething();
    }
}

class Parent3 {
    static int a = 3;

    static {
        System.out.println("Parent3 static block");
    }

    static void doSomething(){
        System.out.println("do something");
    }
}

class Child3 extends Parent3 {
    static {
        System.out.println("Child static block");
    }
}
