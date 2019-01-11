package com.note.base.thread.heima;

public class InterruptedDemo extends Thread {

    public InterruptedDemo(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (!interrupted()) {
            System.out.println(getName() + "线程执行了 .. ");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        InterruptedDemo d1 = new InterruptedDemo("first-thread");
        InterruptedDemo d2 = new InterruptedDemo("second-thread");
        d1.start();
        d2.start();
        d1.interrupt();
    }

}
