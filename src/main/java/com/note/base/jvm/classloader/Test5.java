package com.note.base.jvm.classloader;

/**
 * 当一个接口在初始化时，并不要求其父接口都完成了初始化
 * 只有在真正使用父接口的时候（如引用接口中定义的常量时），才会初始化
 */
public class Test5 {

    public static void main(String[] args) {
        System.out.println(MyChild5.b);
    }
}

interface MyGrandPa {
    public static Thread thread = new Thread() {
        {
            System.out.println("MyGrandPa invoke");
        }
    };
}

interface MyParent5 extends MyGrandPa {
    public static Thread thread = new Thread() {
        {
            System.out.println("MyParent5 invoke");
        }
    };
}

interface MyChild5 extends MyParent5 {
    public static int b = 6;
}

