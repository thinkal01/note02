package com.note.base.thread.heima;

import java.util.Random;

/**
 * ThreadLocal.set(value) -> map.put(Thread.getCurrentThread(), value);
 * ThreadLocal.get() -> map.get(Thread.getCurrentThread());
 * 内存问题：在并发量高的时候，可能有内存溢出。一定注意回收资源问题，线程结束之前将当前线程保存的变量删除
 * ThreadLocal.remove();
 */
public class ThreadLocal01 {
    private static ThreadLocal<MyThreadScopeData> myThreadScopeData = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                int data = new Random().nextInt();
                System.out.println(Thread.currentThread().getName() + " has put data :" + data);
                /*MyThreadScopeData myData = new MyThreadScopeData();
                myData.setName("name" + data);
                myData.setAge(data);
                myThreadScopeData.set(myData);*/
                MyThreadScopeData.getThreadInstance().setName("name" + data);
                MyThreadScopeData.getThreadInstance().setAge(data);
                new A().get();
                new B().get();
            }).start();
        }
    }

    static class A {
        public void get() {
            // MyThreadScopeData myData = myThreadScopeData.get();
            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("A from " + Thread.currentThread().getName() + " getMyData: " + myData.getName() + "," + myData.getAge());
        }
    }

    static class B {
        public void get() {
            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("B from " + Thread.currentThread().getName() + " getMyData: " + myData.getName() + "," + myData.getAge());
        }
    }
}

class MyThreadScopeData {
    //private static MyThreadScopeData instance = null;//new MyThreadScopeData();
    private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<>();
    private String name;
    private int age;

    private MyThreadScopeData() {
    }

    public static /*synchronized*/ MyThreadScopeData getThreadInstance() {
        MyThreadScopeData instance = map.get();
        if (instance == null) {
            instance = new MyThreadScopeData();
            map.set(instance);
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
