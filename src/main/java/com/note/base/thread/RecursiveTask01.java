package com.note.base.thread;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

// 继承RecursiveTask来实现"可分解"的任务
public class RecursiveTask01 extends RecursiveTask<Integer> {
    // 每个“小任务”只最多只累加20个数
    private static final int THRESHOLD = 20;
    private int arr[];
    private int start;
    private int end;

    // 累加从start到end的数组元素
    public RecursiveTask01(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread().getName() + " 进入 " + start + " " + end);
        int sum = 0;
        // 当end与start之间的差小于THRESHOLD时，开始进行实际累加
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            System.out.println(Thread.currentThread().getName() + " 计算完毕..." + start + " " + end);
            return sum;
        } else {
            // 如果当end与start之间的差大于THRESHOLD时，即要累加的数超过20个时
            // 将大任务分解成两个小任务。
            int middle = (start + end) / 2;
            RecursiveTask01 left = new RecursiveTask01(arr, start, middle);
            RecursiveTask01 right = new RecursiveTask01(arr, middle, end);
            // 开启一个新的线程任务
            left.fork();
            right.fork();
            System.out.println(Thread.currentThread().getName() + " join " + start + " " + end);
            // 把两个“小任务”累加的结果合并起来
            return left.join() + right.join();
        }
    }

    public static void main(String[] args) throws Exception {
        int[] arr = new int[1000];
        Random rand = new Random();
        int total = 0;
        // 初始化100个数字元素
        for (int i = 0, len = arr.length; i < len; i++) {
            int tmp = rand.nextInt(20);
            // 对数组元素赋值，并将数组元素的值添加到sum总和中。
            total += (arr[i] = tmp);
        }
        System.out.println(total);
        // ForkJoinPool pool = new ForkJoinPool(20);
        // 创建一个通用池
        ForkJoinPool pool = ForkJoinPool.commonPool();
        // 提交可分解的CalTask任务
        Future<Integer> future = pool.submit(new RecursiveTask01(arr, 0, arr.length));
        System.out.println(future.get());
        // 关闭线程池
        pool.shutdown();
    }
}
