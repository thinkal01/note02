package com.note.designpattern.bjsxt.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * 测试多线程环境下五种创建单例模式的效率
 */
public class Client3 {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        int threadNum = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 1000000; i1++) {
                    // Object o = SingletonDemo4.getInstance();
                    Object o = SingletonDemo5.INSTANCE;
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();    //main线程阻塞，直到计数器变为0，才会继续往下执行！

        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start));
    }
}
