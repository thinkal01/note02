package com.note.base.thread.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueue01 {
    /**
     * 并发容器 - ArrayBlockingQueue
     * 有界容器。
     */
    final BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

    public static void main(String[] args) {
        final ArrayBlockingQueue01 t = new ArrayBlockingQueue01();

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("offer method : " + t.queue.offer("value" + i, 1, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
            }
        }

        System.out.println(t.queue);
    }

}
