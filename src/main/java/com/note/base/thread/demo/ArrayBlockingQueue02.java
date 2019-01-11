package com.note.base.thread.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueue02 {

    public static void main(String[] args) {
        final BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(queue.take() + ":" + (System.currentTimeMillis() / 1000));
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }).start();
        }

        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        // 模拟处理16行日志，下面代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
        // 修改程序代码，开四个线程让这16个对象在4秒钟打完。
        for (int i = 0; i < 16; i++) {
            String log = "" + (i + 1);
            try {
                queue.put(log);
            } catch (InterruptedException e) {
            }
        }
    }

}
