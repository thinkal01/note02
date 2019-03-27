package com.note.base.jvm.classloader;

/**
 * Created by zhouyilin on 2018/4/29.
 */
public class MySample {

    public MySample() {
        System.out.println("MySample is loaded by : " + this.getClass().getClassLoader());

        System.out.println("from MySample : " + MyCat.class);
        new MyCat();
    }
}
