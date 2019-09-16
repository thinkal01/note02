package com.note.base.java8.stream2;


public class LambdaTest {
    // this当前类实例
    Runnable r1 = () -> System.out.println(this);
    // 实现Runnable接口的匿名类
    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this);
        }
    };

    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();
        Thread t1 = new Thread(lambdaTest.r1);
        t1.start();

        System.out.println("--------");

        Thread t2 = new Thread(lambdaTest.r2);
        t2.start();
    }

}
