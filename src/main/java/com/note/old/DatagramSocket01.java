package com.note.old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class DatagramSocket01 {
    public void receive() throws IOException {
        // 创建接收端的Socket对象
        DatagramSocket ds = new DatagramSocket(12345);

        while (true) {
            // 创建一个包裹
            byte[] bys = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bys, bys.length);

            // 接收数据
            ds.receive(dp);

            // 解析数据
            String ip = dp.getAddress().getHostAddress();
            String s = new String(dp.getData(), 0, dp.getLength());
            System.out.println("from " + ip + " data is : " + s);
        }

        // 释放资源
        // 接收端应该一直开着等待接收数据，是不需要关闭
        // ds.close();
    }

    public void send() throws IOException {
        // 创建发送端的Socket对象
        DatagramSocket ds = new DatagramSocket();

        // 封装键盘录入数据
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = br.readLine()) != null) {
            if ("886".equals(line)) {
                break;
            }

            // 创建数据并打包
            byte[] bys = line.getBytes();
            DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName("192.168.12.255"), 12345);

            // 发送数据
            ds.send(dp);
        }

        // 释放资源
        ds.close();
    }

    public void test01() throws UnknownHostException {
        InetAddress inet = InetAddress.getLocalHost();
        System.out.println("本机的ip=" + inet.getHostAddress());

        // InetAddress address = InetAddress.getByName("liuyi");
        InetAddress address = InetAddress.getByName("192.168.12.63");

        // 主机名，IP地址
        String name = address.getHostName();
        String ip = address.getHostAddress();
    }

    // 多线程聊天程序，可以实现在一个窗口发送和接收数据
    public void test02() throws SocketException {
        DatagramSocket dsSend = new DatagramSocket();
        DatagramSocket dsReceive = new DatagramSocket(12306);

        SendThread st = new SendThread(dsSend);
        ReceiveThread rt = new ReceiveThread(dsReceive);

        Thread t1 = new Thread(st);
        Thread t2 = new Thread(rt);

        t1.start();
        t2.start();
    }
}

class SendThread implements Runnable {

    private DatagramSocket ds;

    public SendThread(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        try {
            // 封装键盘录入数据
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    System.in));
            String line = null;
            while ((line = br.readLine()) != null) {
                if ("886".equals(line)) {
                    break;
                }

                // 创建数据并打包
                byte[] bys = line.getBytes();
                // DatagramPacket dp = new DatagramPacket(bys, bys.length,
                // InetAddress.getByName("192.168.12.92"), 12345);
                DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName("192.168.12.255"), 12306);

                // 发送数据
                ds.send(dp);
            }
            // 释放资源
            ds.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReceiveThread implements Runnable {
    private DatagramSocket ds;

    public ReceiveThread(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 创建一个包裹
                byte[] bys = new byte[1024];
                DatagramPacket dp = new DatagramPacket(bys, bys.length);

                // 接收数据
                ds.receive(dp);

                // 解析数据
                String ip = dp.getAddress().getHostAddress();
                String s = new String(dp.getData(), 0, dp.getLength());
                System.out.println("from " + ip + " data is : " + s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
