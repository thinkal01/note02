package com.note.base;

public class StaticExtend { // 父类
    public static String staticStr = "A静态属性";
    public String nonStaticStr = "A非静态属性";

    public static void staticMethod() {
        System.out.println("A静态方法");
    }

    public void nonStaticMethod() {
        System.out.println("A非静态方法");
    }

    public static void main(String[] args) {
        C c = new C();
        System.out.println(c.nonStaticStr);
        System.out.println(c.staticStr);
        // 输出的结果都是父类中的非静态属性、静态属性和静态方法,推出静态属性和静态方法可以被继承
        c.staticMethod();

        System.out.println("-------------------------------");

        StaticExtend c1 = new C();
        System.out.println(c1.nonStaticStr);
        System.out.println(c1.staticStr);
        // 结果同上，输出的结果都是父类中的非静态属性、静态属性和静态方法,推出静态属性和静态方法可以被继承
        c1.staticMethod();

        System.out.println("-------------------------------");

        B b = new B();
        System.out.println(b.nonStaticStr);
        System.out.println(b.staticStr);
        // 输出的结果都是子类中的非静态属性、静态属性和静态方法
        b.staticMethod();

        System.out.println("-------------------------------");

        StaticExtend b1 = new B();
        System.out.println(b1.nonStaticStr);
        System.out.println(b1.staticStr);
        // 结果都是父类的静态方法，说明静态方法不可以被重写，不能实现多态
        b1.staticMethod();
    }
}

// 子类B
class B extends StaticExtend {
    public static String staticStr = "B改写后的静态属性";
    public String nonStaticStr = "B改写后的非静态属性";

    public static void staticMethod() {
        System.out.println("B改写后的静态方法");
    }
}

// 子类C继承A中的所有属性和方法
class C extends StaticExtend {

}
