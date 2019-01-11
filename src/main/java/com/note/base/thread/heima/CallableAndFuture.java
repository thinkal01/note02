package com.note.base.thread.heima;

import java.util.Random;
import java.util.concurrent.*;

public class CallableAndFuture {
    /**
     * Callable和Runnable的区别
     * Runnable run方法是被线程调用的，run方法是异步执行的
     * Callable的call方法，不是异步执行的，是由Future的run方法调用的
     */
    public void test01() {
        FutureTask<Integer> task = new FutureTask<>(() -> 1);
        Thread t = new Thread(task);
        t.start();

        System.out.println("先干点别的。。。");

        Integer result = null;
        try {
            result = task.get();
        } catch (Exception e) {
        }
        System.out.println("线程执行的结果为：" + result);
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(() -> {
            Thread.sleep(2000);
            return "hello";
        });

        System.out.println("等待结果");
        try {
            System.out.println("拿到结果：" + future.get());
        } catch (Exception e) {
        }

        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(threadPool2);
        for (int i = 1; i <= 10; i++) {
            final int seq = i;
            completionService.submit(() -> {
                Thread.sleep(new Random().nextInt(5000));
                return seq;
            });
        }
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (Exception e) {
            }
        }
    }

}
