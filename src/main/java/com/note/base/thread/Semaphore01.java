package com.note.base.thread;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class Semaphore01 {

    public void test01() {
        // 同时只有10个线程可以执行
        Semaphore semaphore = new Semaphore(10);
        while (true) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " is run ...");
                    Thread.sleep(2000);
                    semaphore.release();
                } catch (InterruptedException e) {
                }
            }).start();
        }
    }

    /**
     * 将程序改造成有10个线程来消费产生的数据
     * 每个消费者都需要一秒才能处理完，程序应保证这些消费者线程依次有序地消费数据，
     * 只有上一个消费者消费完后，下一个消费者才能消费数据，下一个消费者是谁都可以，但要保证这些消费者线程拿到的数据是有顺序的。
     */
    @Test
    public void test02() throws InterruptedException {
        final Semaphore semaphore = new Semaphore(1);
        // SynchronousQueue是一个不存储元素的BlockingQueue。
        // 每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    String input = queue.take();
                    Thread.sleep(1000);
                    String output = input + "-" + (System.currentTimeMillis() / 1000);
                    System.out.println(Thread.currentThread().getName() + ":" + output);
                    semaphore.release();
                } catch (InterruptedException e) {
                }
            }).start();
        }

        System.out.println("begin:" + (System.currentTimeMillis() / 1000));

        for (int i = 0; i < 10; i++) {
            queue.put(i + "");
            System.out.println("put " + i);
        }

        TimeUnit.SECONDS.sleep(2);
    }
}
