package com.note.base.jvm.bytecode;

/**
 * 程序运行结果
 * grandpa
 * grandpa
 *
 * 原因是方法的静态分派
 *
 * Grandpa g1 = new Father();
 * 以上代码，g1的静态类型是Grandpa，而g1的实际类型（真正指向的类型）是Father。
 *
 * 变量的静态类型是不会发生变化的，而变量的实际类型是可以发生变化的（多态的一种体现），实际类型是在运行期方可确定。
 */
public class MyTest5 {

    // 方法重载，是一种静态的行为，编译器就可以完全确定。

    public void test(Grandpa grandpa) {
        System.out.println("grandpa");
    }

    public void test(Father father) {
        System.out.println("father");
    }

    public void test(Son son) {
        System.out.println("son");
    }

    public static void main(String[] args) {
        Grandpa g1 = new Father();
        Grandpa g2 = new Son();

        MyTest5 myTest5 = new MyTest5();

        /**
         * test的方法是一个被重载的方法，重载是一种静态行为，以传入变量的静态类型作为重载方法寻找的依据
         * g1 g2变量的静态类型都是Grandpa，因此会调用test(Grandpa grandpa)方法
         */
        myTest5.test(g1);
        myTest5.test(g2);
    }

}

class Grandpa {

}

class Father extends Grandpa {

}

class Son extends Father {

}
