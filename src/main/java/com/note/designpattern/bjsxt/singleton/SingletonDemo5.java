package com.note.designpattern.bjsxt.singleton;

/**
 * 枚举只有一个成员时,就可以作为一种单例的实现方式
 * 测试枚举式实现单例模式(没有延时加载)
 */
public enum SingletonDemo5 {
    //这个枚举元素，本身就是单例对象！
    INSTANCE;

    //添加自己需要的操作！
    public void singletonOperation() {
    }

    public static void main(String[] args) {
        System.out.println(SingletonDemo5.INSTANCE == SingletonDemo5.INSTANCE);
    }
}
