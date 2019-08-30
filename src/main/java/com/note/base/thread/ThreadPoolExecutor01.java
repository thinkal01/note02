package com.note.base.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutor01 {
    public void main() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 50, 10, TimeUnit.DAYS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                count.getAndIncrement();
            });
        }
        threadPool.shutdown();
        while (Thread.activeCount() > 1) {
        }
        System.out.println(count.get());
    }

    public void main1() {
        // 10个线程来处理大量的任务
        // ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        // ExecutorService pool = Executors.newFixedThreadPool(10);
        // ExecutorService pool = Executors.newCachedThreadPool();
        // ExecutorService pool = Executors.newSingleThreadExecutor();
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
        // ExecutorService pool = Executors.newWorkStealingPool();

        while (true) {
            Future f = (Future) pool.submit(() -> {
            });

            pool.schedule(() -> System.out.println(Thread.currentThread().getName()), 5, TimeUnit.SECONDS);

            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            });
        }
    }

}
