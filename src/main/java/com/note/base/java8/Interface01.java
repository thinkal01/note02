package com.note.base.java8;

interface Inter {

    //抽象方法
    void show();
    //default方法

    default void defaultPrint() {
    }
    //static方法

    static void staticPrint() {
    }
}

//实现类
class InterImpl implements Inter {

    public void show() {
    }
}

public class Interface01 {
    public static void main(String[] args) {
        Inter.staticPrint();
        Inter i = new InterImpl();
        i.defaultPrint();
        i.show();
    }
}