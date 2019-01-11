package com.note.base.java8;

public enum Direction {
    FRONT("前") {
        @Override
        public void show() {
            System.out.println("前");
        }
    },
    BEHIND("后") {
        @Override
        public void show() {
            System.out.println("后");
        }
    },
    LEFT("左") {
        @Override
        public void show() {
            System.out.println("左");
        }
    },
    RIGHT("右") {
        @Override
        public void show() {
            System.out.println("右");
        }
    };

    private String name;

    // 枚举类也是类，也可以有构造器、方法和属性
    // 构造器不能给出访问修饰，而且默认都是private构造器。因为枚举类的实例不能让外界来创建！
    Direction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void show();
}

class TestDirection {
    public static void main(String[] args) {
        Direction d = Direction.FRONT;
        // 枚举常量的名称
        d.name();
        // 枚举常量hashcode值
        d.hashCode();
        // 判断是否相等
        d.equals(Direction.FRONT);
        // FRONT
        System.out.println(d);
        // 前
        System.out.println(d.getName());
        // 0
        System.out.println(d.ordinal());
        // FRONT
        System.out.println(d.toString());
        // 比较的就是枚举常量在枚举类中声明的顺序，例如FRONT的下标为0，BEHIND下标为1，那么FRONT小于BEHIND
        // -1
        System.out.println(d.compareTo(Direction.BEHIND));
        d.show();

        Direction direction = Direction.FRONT;
        switch (direction) {
            // 编译器会根据switch中d的类型来判定每个枚举类型，在case中必须直接给出与d相同类型的枚举选项，而不能再有类型。
            case FRONT:
                System.out.println("你选择了前");
                break;
            case BEHIND:
                System.out.println("你选择了后");
                break;
            case LEFT:
                System.out.println("你选择了左");
                break;
            case RIGHT:
                System.out.println("你选择了右");
                break;
        }

        d = Direction.valueOf("FRONT");
        d = Enum.valueOf(Direction.class, "FRONT");
        // 此方法虽然在JDK文档中查找不到，但每个枚举类都具有该方法，它遍历枚举类的所有枚举值非常方便
        Direction[] dirs = Direction.values();
        for (Direction d2 : dirs) {
        }
    }
}