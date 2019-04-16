package com.note.base.jvm.classloader;

/**
 * Created by zhouyilin on 2018/4/19.
 *
 *
 */
public class Test8 {

    public static void main(String[] args) {
        System.out.println(FinalTest.x);
    }
}

class FinalTest {
    public static final int x = 1; // x = new Random().nextInt(3); 时，静态代码块会执行

    static {
        System.out.println("FinalTest static block");
    }
}