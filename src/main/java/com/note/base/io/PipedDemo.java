package com.note.base.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

class Send implements Runnable {       // 线程类
    private PipedOutputStream pos;    // 管道输出流

    public Send() {
        this.pos = new PipedOutputStream();
    }

    public void run() {
        String str = "Hello World!!!";
        try {
            this.pos.write(str.getBytes());
        } catch (IOException e) {
        } finally {
            try {
                this.pos.close();
            } catch (IOException e) {
            }
        }
    }

    public PipedOutputStream getPos() {
        return this.pos;
    }
};

class Receive implements Runnable {
    private PipedInputStream pis;    // 管道输入流

    public Receive() {
        this.pis = new PipedInputStream();
    }

    public void run() {
        byte b[] = new byte[1024];
        int len;
        try {
            // 读取内容
            len = this.pis.read(b);
            System.out.println("接收的内容为：" + new String(b, 0, len));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.pis.close();
            } catch (IOException e) {
            }
        }
    }

    public PipedInputStream getPis() {
        return this.pis;
    }
};

public class PipedDemo {
    public static void main(String args[]) {
        Send s = new Send();
        Receive r = new Receive();
        try {
            // 连接管道
            // s.getPos().connect(r.getPis());
            r.getPis().connect(s.getPos());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 启动线程
        new Thread(s).start();
        new Thread(r).start();
    }
};