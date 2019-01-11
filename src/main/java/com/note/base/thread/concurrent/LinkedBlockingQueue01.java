package com.note.base.thread.concurrent;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingQueue01 {
    /**
     * 并发阻塞容器 - LinkedBlockingQueue
     * put 队列容量满后，自动阻塞
     * take 队列容量为0后，自动阻塞。
     */
    final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    final Random r = new Random();

    public static void main(String[] args) {
        final LinkedBlockingQueue01 t = new LinkedBlockingQueue01();

        new Thread(() -> {
            while (true) {
                try {
                    t.queue.put("value" + t.r.nextInt(1000));
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
            }
        }, "producer").start();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " - " + t.queue.take());
                    } catch (InterruptedException e) {
                    }
                }
            }, "consumer" + i).start();
        }
    }

}
