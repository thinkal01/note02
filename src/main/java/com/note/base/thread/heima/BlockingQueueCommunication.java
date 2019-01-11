package com.note.base.thread.heima;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueCommunication {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                business.sub(i);
            }
        }).start();

        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }
    }

    static class Business {
        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<>(1);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<>(1);

        {
            // Collections.synchronizedMap(null);
            try {
                queue2.put(1);
            } catch (InterruptedException e) {
            }
        }

        public void sub(int i) {
            try {
                queue1.put(1);
            } catch (InterruptedException e) {
            }
            for (int j = 1; j <= 10; j++) {
                System.out.println("sub thread sequece of " + j + ",loop of " + i);
            }
            try {
                queue2.take();
            } catch (InterruptedException e) {
            }
        }

        public void main(int i) {
            try {
                queue2.put(1);
            } catch (InterruptedException e1) {
            }
            for (int j = 1; j <= 100; j++) {
                System.out.println("main thread sequece of " + j + ",loop of " + i);
            }
            try {
                queue1.take();
            } catch (InterruptedException e) {
            }
        }
    }
}
