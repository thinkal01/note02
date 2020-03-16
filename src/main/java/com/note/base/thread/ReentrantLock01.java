package com.note.base.thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock01 {
    Lock lock = new ReentrantLock();

    @Test
    public void test() {
        try {
            // lock.lock();
            Condition condition = lock.newCondition();
            // condition.await(100, TimeUnit.MILLISECONDS);
            condition.signal();
            Thread.sleep(500);
            // Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * tryLock
     */
    @Test
    public void tryLock01() {
        final ReentrantLock01 t = new ReentrantLock01();
        new Thread(() -> t.m1()).start();
        new Thread(() -> t.m2()).start();

        // 主线程等待
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void lockInterruptibly() {
        final ReentrantLock01 t = new ReentrantLock01();
        new Thread(() -> t.m1()).start();

        Thread t2 = new Thread(() -> t.m3());
        t2.start();
        // 打断线程休眠。非正常结束阻塞状态的线程，都会抛出异常。
        t2.interrupt();
    }

    /**
     * 公平锁
     */
    @Test
    public void fairLock() {
        FairLock01 t = new FairLock01();
        // TestSync t = new TestSync();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        // 一般两线程交替执行
        t1.start();
        t2.start();
    }

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("m1() method " + i);
            }
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }

    /**
     * 尝试锁
     */
    void m2() {
        boolean isLocked = false;
        try {
            // 无法获取锁标记，返回false。
            // 如果获取锁标记，返回true
            isLocked = lock.tryLock();

            // 阻塞尝试锁，阻塞参数代表的时长，尝试获取锁标记。
            // 如果超时，不等待。直接返回。
            // isLocked = lock.tryLock(5, TimeUnit.SECONDS);
            if (isLocked) {
                System.out.println("m2() method synchronized");
            } else {
                System.out.println("m2() method unsynchronized");
            }
        } catch (Exception e) {
        } finally {
            if (isLocked) {
                // 尝试锁在解除锁标记的时候，一定要判断是否获取到锁标记。
                // 如果当前线程没有获取到锁标记，会抛出异常。
                lock.unlock();
            }
        }
        // 建议以下写法
        /*if (lock.tryLock()) {
            try {
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }*/
    }

    /**
     * 可打断
     * 阻塞状态： 包括普通阻塞，等待队列，锁池队列。
     * 普通阻塞： sleep(10000)， 可以被打断。调用thread.interrupt()方法，可以打断阻塞状态，抛出异常。
     * 等待队列： wait()也是一种阻塞状态，只能由notify唤醒。无法打断
     * 锁池队列： 无法获取锁标记。不是所有的锁池队列都可被打断。
     * 使用ReentrantLock的lock方法，获取锁标记的时候，如果需要阻塞等待锁标记，无法被打断。
     * 使用ReentrantLock的lockInterruptibly方法，获取锁标记的时候，如果需要阻塞等待，可以被打断。
     */
    void m3() {
        boolean interruptFlag = false;
        try {
            // 阻塞等待锁。可以被其他线程打断阻塞状态
            lock.lockInterruptibly();
            System.out.println("m2() method");
        } catch (InterruptedException e) {
            interruptFlag = true;
            System.out.println("m2() interrupted");
        } finally {
            if (!interruptFlag) {
                System.out.println("m2() unlock");
                lock.unlock();
            }
        }
    }
}

class FairLock01 implements Runnable {
    // 定义一个公平锁
    private static ReentrantLock lock = new ReentrantLock(true);

    public void run() {
        for (int i = 0; i < 50; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get lock");
            } finally {
                lock.unlock();
            }
        }
    }
}

class TestSync implements Runnable {
    // 定义一个非公平锁
    private static ReentrantLock lock = new ReentrantLock();

    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " get lock");
            lock.unlock();
        }
    }
}