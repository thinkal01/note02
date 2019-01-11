package com.note.base.thread;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Condition02 {
    private int signal;
    Lock lock = new ReentrantLock();
    Condition a = lock.newCondition();
    Condition b = lock.newCondition();
    Condition c = lock.newCondition();

    public void a() {
        lock.lock();
        while (signal != 0) {
            try {
                a.await();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("a");
        signal++;
        b.signal();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        while (signal != 1) {
            try {
                b.await();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("b");
        signal++;
        c.signal();
        lock.unlock();
    }

    public void c() {
        lock.lock();
        while (signal != 2) {
            try {
                c.await();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("c");
        signal = 0;
        a.signal();
        lock.unlock();
    }

    class A implements Runnable {
        private Condition02 Condition02;

        public A(Condition02 Condition02) {
            this.Condition02 = Condition02;
        }

        @Override
        public void run() {
            while (true) {
                Condition02.a();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }

    }

    class B implements Runnable {
        private Condition02 Condition02;

        public B(Condition02 Condition02) {
            this.Condition02 = Condition02;
        }

        @Override
        public void run() {
            while (true) {
                Condition02.b();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    class C implements Runnable {
        private Condition02 Condition02;

        public C(Condition02 Condition02) {
            this.Condition02 = Condition02;
        }

        @Override
        public void run() {
            while (true) {
                Condition02.c();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    @Test
    /*
     * 顺序打印 a b c a b c
     */
    public void main() throws InterruptedException {
        Condition02 d = new Condition02();
        A a = new A(d);
        B b = new B(d);
        C c = new C(d);

        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();

        Thread.sleep(5000);
    }
}

