package com.note.designpattern;

public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
    }

    /**
     * 双重检查加锁
     */
    public static Singleton getInstance() {
        // 自旋   while(true)
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();  // 指令重排序
                }
            }
        }

        return instance;
    }

}
