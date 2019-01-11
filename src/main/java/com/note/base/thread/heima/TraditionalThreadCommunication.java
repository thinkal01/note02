package com.note.base.thread.heima;

public class TraditionalThreadCommunication {
    public static void main(String[] args) {
        // 子线程循环10次，接着主线程循环10 ，接着又回到子线程循环10次，接着再回到主线程又循环10 ，如此循环50次
        Business business = new Business();
        new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                business.sub(i);
            }
        }).start();

        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }
    }
}

class Business {
    private boolean bShouldSub = true;

    public synchronized void sub(int i) {
        if (!bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        for (int j = 1; j <= 1; j++) {
            System.out.println("sub thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = false;
        this.notify();
    }

    public synchronized void main(int i) {
        if (bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        for (int j = 1; j <= 2; j++) {
            System.out.println("main thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = true;
        this.notify();
    }
}
