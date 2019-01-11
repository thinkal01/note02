package com.note.base.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SellTicket01 {
    public static void main() {
        // 创建资源对象
        SellTicket st = new SellTicket();

        // 创建三个窗口
        Thread t1 = new Thread(st, "窗口1");
        Thread t2 = new Thread(st, "窗口2");
        Thread t3 = new Thread(st, "窗口3");

        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }
}

class SellTicket implements Runnable {
    // 定义票
    private int tickets = 100;

    // 定义锁对象
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                // 加锁
                lock.lock();
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "正在出售第" + (tickets--) + "张票");
                }
            } finally {
                // 释放锁
                lock.unlock();
            }
        }
    }

}
