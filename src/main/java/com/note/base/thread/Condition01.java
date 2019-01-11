package com.note.base.thread;

public class Condition01 {
    private int signal;

    public synchronized void a() {
        while (signal != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("a");
        signal++;
        notifyAll();
    }

    public synchronized void b() {
        while (signal != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("b");
        signal++;
        notifyAll();
    }

    public synchronized void c() {
        while (signal != 2) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("c");
        signal = 0;
        notifyAll();
    }

    public static void main(String[] args) {
        // 三个线程顺序执行
        Condition01 d = new Condition01();
        A a = new A(d);
        B b = new B(d);
        C c = new C(d);

        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();
    }
}

class A implements Runnable {
    private Condition01 Condition01;

    public A(Condition01 Condition01) {
        this.Condition01 = Condition01;
    }

    @Override
    public void run() {
        while (true) {
            Condition01.a();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class B implements Runnable {
    private Condition01 Condition01;

    public B(Condition01 Condition01) {
        this.Condition01 = Condition01;
    }

    @Override
    public void run() {
        while (true) {
            Condition01.b();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class C implements Runnable {
    private Condition01 Condition01;

    public C(Condition01 Condition01) {
        this.Condition01 = Condition01;
    }

    @Override
    public void run() {
        while (true) {
            Condition01.c();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}