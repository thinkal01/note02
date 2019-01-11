package com.note.base.thread.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueue01 {
    /**
     * 并发容器 - DelayQueue
     * 无界容器。
     */
    static BlockingQueue<MyTask> queue = new DelayQueue<>();

    public static void main(String[] args) throws InterruptedException {
        long value = System.currentTimeMillis();
        MyTask task1 = new MyTask(value + 2000);
        MyTask task2 = new MyTask(value + 1000);
        MyTask task3 = new MyTask(value + 3000);
        MyTask task4 = new MyTask(value + 2500);
        MyTask task5 = new MyTask(value + 1500);

        queue.put(task1);
        queue.put(task2);
        queue.put(task3);
        queue.put(task4);
        queue.put(task5);

        System.out.println(queue);
        System.out.println(value);
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.take());
        }
    }

    static class MyTask implements Delayed {
        private long compareValue;

        public MyTask(long compareValue) {
            this.compareValue = compareValue;
        }

        /**
         * 比较大小。自动实现升序
         * 建议和getDelay方法配合完成。
         * 如果在DelayQueue是需要按时间完成的计划任务，必须配合getDelay方法完成。
         */
        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        /**
         * 获取计划时长的方法。
         * 根据参数TimeUnit来决定，如何返回结果值。
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(compareValue - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public String toString() {
            return "Task compare value is : " + this.compareValue;
        }

    }
}

