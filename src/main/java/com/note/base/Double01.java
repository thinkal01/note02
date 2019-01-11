package com.note.base;

public class Double01 {
    public void test01() {
        // Infinity(无穷大)
        double d = 1.0 / 0.0;
        // double 初始化为无穷大
        // Double.POSITIVE_INFINITY
        // Double.NEGATIVE_INFINITY
    }

    public static void main(String[] args) {
        //把double赋值给float，加了强制类型转换
        double d = 12.345;
        float f = (float) d;

        //下面两个定义有区别
        float f1 = (float) 12.345;
        float f2 = 12.345F;
    }
}
