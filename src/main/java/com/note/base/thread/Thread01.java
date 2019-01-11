package com.note.base.thread;

import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Thread01 {
    private static void method2() {
        ThreadGroup tg = new ThreadGroup("这是一个新的组");
        MyRunnable my = new MyRunnable();

        Thread t1 = new Thread(tg, my, "林青霞");
        Thread t2 = new Thread(tg, my, "刘意");

        System.out.println(t1.getThreadGroup().getName());
        System.out.println(t2.getThreadGroup().getName());

        //通过组名称设置后台线程，表示该组的线程都是后台线程
        tg.setDaemon(true);
    }

    @Test
    public void method1() {
        MyRunnable my = new MyRunnable();
        Thread t1 = new Thread(my, "林青霞");
        Thread t2 = new Thread(my, "刘意");

        ThreadGroup tg1 = t1.getThreadGroup();
        ThreadGroup tg2 = t2.getThreadGroup();

        String name1 = tg1.getName();
        String name2 = tg2.getName();

        // 默任情况下，所有的线程都属于同一个组属于main线程组
        System.out.println(Thread.currentThread().getThreadGroup().getName());
    }

    public void test03() throws ParseException {
        // 创建定时器对象
        Timer t = new Timer();
        // 3秒后执行爆炸任务,然后结束任务
        t.schedule(new MyTask(t), 3000);
        // 3秒后执行任务，每隔2秒再继续
        t.schedule(new MyTask(), 3000, 2000);

        String s = "2014-11-27 15:45:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse(s);
        // 在指定的时间执行
        t.schedule(new DeleteFolder(), d);
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int x = 0; x < 100; x++) {
            System.out.println(Thread.currentThread().getName() + ":" + x);
        }
    }

}


class MyTask extends TimerTask {

    private Timer t;

    public MyTask() {

    }

    public MyTask(Timer t) {
        this.t = t;
    }

    @Override
    public void run() {
        t.cancel();
    }
}

class DeleteFolder extends TimerTask {

    @Override
    public void run() {
        File srcFolder = new File("demo");
        deleteFolder(srcFolder);
    }

    // 递归删除目录
    public void deleteFolder(File srcFolder) {
        File[] fileArray = srcFolder.listFiles();
        if (fileArray != null) {
            for (File file : fileArray) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    System.out.println(file.getName() + ":" + file.delete());
                }
            }
            System.out.println(srcFolder.getName() + ":" + srcFolder.delete());
        }
    }
}