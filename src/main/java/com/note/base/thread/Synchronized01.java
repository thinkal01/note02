/**
 * synchronized关键字
 * 锁对象变更问题
 * 同步代码一旦加锁后，那么会有一个临时的锁引用执行锁对象，和真实的引用无直接关联。
 * 在锁未释放之前，修改锁对象引用，不会影响同步代码的执行。
 */
package com.note.base.thread;

import java.util.concurrent.TimeUnit;

public class Synchronized01 {
    Object o = new Object();

    void m() {
        System.out.println(Thread.currentThread().getName() + " start");
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                System.out.println(Thread.currentThread().getName() + " - " + o);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Synchronized01 t = new Synchronized01();

        new Thread(() -> t.m(), "thread1").start();

        TimeUnit.SECONDS.sleep(3);

        t.o = new Object();
        // 可以运行
        new Thread(() -> t.m(), "thread2").start();
    }

}
