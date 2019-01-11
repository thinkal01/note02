package com.note.base.java8;

public abstract class MyEnum {
    // 创建几个实例
    public static final MyEnum FRONT = new MyEnum("前") {
        @Override
        public void show() {
            System.out.println("前");
        }

    };
    public static final MyEnum BEHIND = new MyEnum("后") {
        @Override
        public void show() {
            System.out.println("后");
        }

    };
    public static final MyEnum LEFT = new MyEnum("左") {
        @Override
        public void show() {
            System.out.println("左");
        }

    };
    public static final MyEnum RIGHT = new MyEnum("右") {
        @Override
        public void show() {
            System.out.println("右");
        }

    };

    // 加入成员变量
    private String name;

    // 构造私有
    private MyEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 加入抽象方法
    public abstract void show();
}
