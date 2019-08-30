package com.note.base.thread.heima;

import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPoolTest {
    @Test
    public void test02() {
        // 当前机器CPU数量,逻辑核心数
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        // 线程池设置多大比较合理
        // IO密集型=2Ncpu（可以测试后控制大小，2Ncpu一般没问题,常出现于数据库交互、文件上传下载、网络数据传输等）
        // 计算密集型=Ncpu（常出现于线程中：复杂算法）
    }

    public static void main(String[] args) {
        /**
         * 固定容量线程池
         * ExecutorService - 线程池服务类型。所有的线程池类型都实现这个接口。代表可以提供线程池能力。
         * Executors - Executor的工具类。类似Collections,可以更简单的创建若干种线程池。
         * FixedThreadPool - 固定容量线程池。构造的时候，提供线程池最大容量。
         * shutdown - 优雅关闭。不是强行关闭线程池，回收线程池中的资源。而是不再处理新的任务，将已接收的任务处理完毕后再关闭。
         */
        // ExecutorService service = Executors.newFixedThreadPool(3);
        // 模拟fixedThreadPool，核心线程5个，最大容量5个，线程的生命周期无限。
        // ExecutorService service = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        /**
         * 无容量限制的线程池（最大容量默认为Integer.MAX_VALUE）
         */
        //ExecutorService service = Executors.newCachedThreadPool();
        /**
         * 容量为1的线程池。顺序执行。
         */
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 10; i++) {
            int task = i;
            service.execute(() -> {
                for (int j = 1; j <= 10; j++) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for  task of " + task);
                }
            });
        }
        System.out.println(service);

        // service.shutdown();
        // 是否已经结束，相当于回收了资源
        System.out.println(service.isTerminated());
        // 是否已经关闭，是否调用过shutdown方法
        System.out.println(service.isShutdown());
        System.out.println(service);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
        }

        System.out.println("all of 10 tasks have committed! ");
        System.out.println(service);

        try {
            // 优雅关闭
            service.shutdown();
            // 5秒内,所有任务都结束返回TRUE
            if (!service.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                // 超时的时候向线程池中所有的线程发出中断(interrupted)
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            // awaitTermination方法被中断的时候也中止线程池中全部的线程的执行
            System.out.println("awaitTermination interrupted: " + e);
            // 强制关闭
            service.shutdownNow();
        }

    }

    public void scheduledThreadPool() {
        /**
         * 线程池
         * 计划任务线程池。
         */
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

        // 定时完成任务。 scheduleAtFixedRate(Runnable, start_limit, limit, timeunit)
        // runnable - 要执行的任务。
        service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 300, TimeUnit.MILLISECONDS);


        // 指定时间和频率
        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(() -> System.out.println("bombing!"), 6, 2, TimeUnit.SECONDS);
    }

    public void future() throws Exception {
        FutureTask<String> task = new FutureTask<>(() -> "first future task");
        new Thread(task).start();
        System.out.println(task.get());

        ExecutorService service = Executors.newFixedThreadPool(1);

        Future<String> future = service.submit(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
            }
            return Thread.currentThread().getName() + " - test executor";
        });
        System.out.println(future);
        System.out.println(future.isDone()); // 查看线程是否结束，call方法是否执行结束
        System.out.println(future.get()); // 获取call方法的返回值。
    }

    /**
     * 线程池
     * Executor - 线程池底层处理机制。在使用线程池的时候，底层如何调用线程中的逻辑。
     */
    class Executor01 implements Executor {
        public void main() {
            new Executor01().execute(() -> System.out.println(Thread.currentThread().getName() + " - test executor"));
        }

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }

}
