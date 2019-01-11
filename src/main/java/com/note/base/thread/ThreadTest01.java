package com.note.base.thread;

import java.util.concurrent.TimeUnit;

public class ThreadTest01 {
    public void test01() {
        try {
            // 休眠1秒
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
