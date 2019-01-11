package com.note.base.thread.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

// 并行访问同一个容器。保证获取容器中数据时没有错误，且线程安全。如：售票，秒杀等业务。
public class ConcurrentLinkedQueue02 {
    /**
     * 普通容器
     */
    static List<String> list = new ArrayList<>();
    // static List<String> list = new Vector<>();

    static {
        for (int i = 0; i < 100000; i++) {
            list.add("String " + i);
        }
    }

    @Test
    public void list() {
        /*for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (list.size() > 0) {
                    // IndexOutOfBoundsException
                    System.out.println(Thread.currentThread().getName() + " - " + list.remove(0));
                }
            }, "Thread" + i).start();
        }*/

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (list) {
                        if (list.size() <= 0) {
                            break;
                        }
                        System.out.println(Thread.currentThread().getName() + " - " + list.remove(0));
                    }
                }
            }, "Thread" + i).start();
        }
    }

    static Queue<String> queue = new ConcurrentLinkedQueue<>();

    @Test
    public void queue() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String str = queue.poll();
                    if (str == null) {
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + " - " + str);
                }
            }, "Thread" + i).start();
        }
    }

}
