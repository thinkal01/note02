package com.note.base.thread.demo;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test03 extends Thread {
    private TestDo testDo;
    private String key;
    private String value;

    public Test03(String key, String key2, String value) {
        this.testDo = TestDo.getInstance();
        /*常量 "1" 和 "1" 是同一个对象，下面这行代码就是要用 "1" + "" 的方式产生新的对象，
        以实现内容没有改变，仍然相等（都还为 "1"），但对象却不再是同一个的效果*/
        this.key = key + key2;
        this.value = value;
    }

    public static void main(String[] args) {
        /*
        如果有几个线程调用TestDo.doSome(key, value)方法时，传递进去的key相等（equals比较为true），
        则这几个线程应互斥排队输出结果，即当有两个线程的key都是"1"时，它们中的一个要比另外其他线程晚1秒输出结果
        */
        Test03 a = new Test03("1", "", "1");
        Test03 b = new Test03("1", "", "2");
        Test03 e = new Test03("1", "", "5");
        Test03 c = new Test03("3", "", "3");
        Test03 d = new Test03("4", "", "4");
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }

    public void run() {
        testDo.doSome(key, value);
    }

    static class TestDo {
        private static TestDo _instance = new TestDo();

        public static TestDo getInstance() {
            return _instance;
        }

        // private ArrayList keys = new ArrayList();
        private CopyOnWriteArrayList keys = new CopyOnWriteArrayList();

        public void doSome(Object key, String value) {
            Object o = key;
            // 有安全隐患,可能会被添加多次
            if (!keys.contains(o)) {
                keys.add(o);
            } else {
                for (Iterator iter = keys.iterator(); iter.hasNext(); ) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                    }
                    Object oo = iter.next();
                    if (oo.equals(o)) {
                        o = oo;
                        break;
                    }
                }
            }

            synchronized (o) {// 大括号内是需要局部同步的代码，不能改动!
                try {
                    Thread.sleep(1000);
                    System.out.println(key + ":" + value + ":" + (System.currentTimeMillis() / 1000));
                } catch (InterruptedException e) {
                }
            }
        }
    }

}

