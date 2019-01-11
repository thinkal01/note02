package com.note.base.thread;

import java.util.concurrent.Exchanger;

public class Exchanger01 {

    public void a(Exchanger<String> exch) {
        System.out.println("a 方法执行...");
        try {
            System.out.println("a 线程正在抓取数据...");
            Thread.sleep(2000);
            System.out.println("a 线程抓取到数据...");
        } catch (InterruptedException e) {
        }

        String res = "12345";

        try {
            System.out.println("a 等待对比结果...");
            exch.exchange(res);
        } catch (InterruptedException e) {
        }

    }

    public void b(Exchanger<String> exch) {
        System.out.println("b 方法开始执行...");
        try {
            System.out.println("b 方法开始抓取数据...");
            Thread.sleep(4000);
            System.out.println("b 方法抓取数据结束...");
        } catch (InterruptedException e) {
        }

        String res = "12345";

        try {
            String value = exch.exchange(res);
            System.out.println("开始进行比对...");
            System.out.println("比对结果为：" + value.equals(res));
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        Exchanger01 d = new Exchanger01();
        Exchanger<String> exch = new Exchanger<>();
        new Thread(() -> d.a(exch)).start();
        new Thread(() -> d.b(exch)).start();
    }

}
