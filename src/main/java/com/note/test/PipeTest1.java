package com.note.test;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 生产者每5秒提供5个产品，放入管道
 */
class MyProducer extends Thread {
    private PipedOutputStream outputStream;
    private int index = 0;

    public MyProducer(PipedOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (int i = 0; i < 5; i++) {
                    outputStream.write(++index);
                }
                Thread.sleep(5000);
            } catch (Exception e) {
            }
        }
    }
}

/**
 * 消费者每0.5秒从管道中取1件产品，打印剩余产品数量，并打印产品信息
 */
class MyConsumer extends Thread {
    private PipedInputStream inputStream;

    public MyConsumer(PipedInputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
                int count = inputStream.available();
                if (count > 0) {
                    System.out.println("rest product count: " + count);
                    System.out.println("get product: " + inputStream.read());
                }
            } catch (Exception e) {
            }
        }
    }
}

public class PipeTest1 {

    public static void main(String[] args) {
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream();
        try {
            pis.connect(pos);
        } catch (IOException e) {
        }

        new MyProducer(pos).start();
        new MyConsumer(pis).start();
    }
}
