package com.note.base.thread.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// 启动两个线程。线程1向容器中新增10个数据。线程2监听容器元素数量，当容器元素数量为5时，线程2输出信息并终止。
public class Demo01 {
    @Test
    public void sleep() {
        final Container t = new Container();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("add Object to Container " + i);
                t.add(new Object());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (t.size() == 5) {
                    System.out.println("size = 5");
                    break;
                }
            }
        }).start();
    }

    /**
     * 方法1 wait notify
     */
    @Test
    public void notify01() {
        final Container t = new Container();
        final Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("add Object to Container " + i);
                    t.add(new Object());
                    if (t.size() == 5) {
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                if (t.size() != 5) {
                    try {
                        lock.wait(); // 线程进入等待队列。
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("size = 5");
                lock.notifyAll(); // 唤醒其他等待线程
            }
        }).start();
    }

    /**
     * 方法2 CountDownLatch 门闩
     */
    @Test
    public void countDownLatch() {
        final Container t = new Container();
        final CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            if (t.size() != 5) {
                try {
                    latch.await(); // 等待门闩的开放。 不是进入等待队列
                } catch (InterruptedException e) {
                }
            }
            System.out.println("size = 5");
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("add Object to Container " + i);
                t.add(new Object());
                if (t.size() == 5) {
                    latch.countDown(); // 门闩-1
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }

    class Container {
        volatile List<Object> container = new ArrayList<>();

        public void add(Object o) {
            this.container.add(o);
        }

        public int size() {
            return this.container.size();
        }
    }
}

