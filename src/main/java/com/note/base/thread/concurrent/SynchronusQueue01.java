package com.note.base.thread.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronusQueue01 {
    /**
     * 并发容器 - SynchronousQueue
     * 同步队列，是一个容量为 0 的队列。是一个特殊的 TransferQueue。
     * 必须有消费线程等待，才能使用的队列。
     * add 方法，无阻塞。若没有消费线程阻塞等待数据，则抛出异常。
     * put 方法，有阻塞。若没有消费线程阻塞等待数据，则阻塞。
     */
    BlockingQueue<String> queue = new SynchronousQueue<>();

    public static void main(String[] args) {
        final SynchronusQueue01 t = new SynchronusQueue01();

        new Thread(() -> {
            try {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                }
                System.out.println(Thread.currentThread().getName() + " - " + t.queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "output thread").start();

        try {
            t.queue.put("test put");
        } catch (InterruptedException e) {
        }

        System.out.println(Thread.currentThread().getName() + " queue size : " + t.queue.size());
    }

}
