package com.note.base;

import org.junit.Test;

import java.util.Scanner;

public class Integer01 {
    @Test
    public void test01() {
        String s = "100";
        Integer i = new Integer(s);

        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);

        // 十进制到其他进制
        System.out.println(Integer.toBinaryString(100));
        System.out.println(Integer.toOctalString(100));
        System.out.println(Integer.toHexString(100));

        System.out.println(Integer.toString(100, 10));
        System.out.println(Integer.toString(100, 2));
        System.out.println(Integer.toString(100, 8));
        System.out.println(Integer.toString(100, 16));

        //其他进制到十进制
        int i1 = Integer.parseInt("000123"); // 123
        System.out.println(Integer.parseInt("100", 10));
        // 4
        System.out.println(Integer.parseInt("100", 2));
        // 64
        System.out.println(Integer.parseInt("100", 8));
        // 256
        System.out.println(Integer.parseInt("100", 16));
    }

    public void test02() {
        // int -- String
        int number = 100;
        String s1 = "" + number;

        String s2 = String.valueOf(number);

        Integer i = new Integer(number);
        String s3 = i.toString();

        String s4 = Integer.toString(number);

        // String -- int
        String s = "100";
        Integer ii = new Integer(s);
        int x = ii.intValue();

        int y = Integer.parseInt(s);
    }

    @Test
    public void test03() {
        // 通过查看源码，我们就知道了，针对-128到127之间的数据
        // 做了一个数据缓冲池，如果数据是该范围内的，每次并不创建新的空间
        // 享元模式（Flyweight Pattern）：复用内存中已存在的对象，减少系统创建对象实例。

        // Integer ii = Integer.valueOf(127);
        Integer i1 = new Integer(127);
        Integer i2 = new Integer(127);
        // false
        System.out.println(i1 == i2);

        Integer i7 = 127;
        Integer i8 = 127;
        // true
        System.out.println(i7 == i8);
    }

    public void swap() {
        // 最有效率的方式算出2乘以8等于几？
        System.out.println(2 << 3);

        // 对两个整数变量的值进行互换
        int a = 2, b = 1;
        //方式1：使用第三方变量(开发中用的)
        int c = a;
        a = b;
        b = c;

        // 如果两个整数的数值过大，会超出int范围，会强制转换。数据会变化
        a = a + b;
        b = a - b;
        a = a - b;

        // 异或运算中，一个数异或同一个数两次，结果还是这个数本身，如 a^b^b=a
        a = a ^ b; //a = 3 ^ 5;
        b = a ^ b; //b = (3^5)^5; b = 3;
        a = a ^ b; //a = (3^5)^3; a = 5;

        //方式4：一句话搞定
        b = (a + b) - (a = b); //b=30-20=10,a=20
    }

    public void test06() {
        // 一、强制类型转换
        long ll = 300000;
        int ii = (int) ll;

        // 二、调用intValue()方法
        ll = 300000;
        ii = new Long(ll).intValue();

        // 三、先把long转换成字符串String，然后在转成Integer
        ll = 300000;
        ii = Integer.parseInt(String.valueOf(ll));
    }

    // 整数反转
    @Test
    public void reverse() {
        int n = 12345;
        int combination = 0;
        do {
            combination = combination * 10 + (n % 10);
            n /= 10;
        } while (n > 0);
    }

    public void reverse2() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] reverse_N = new int[20];
        int i = 0;
        for (; n > 0; ++i) {
            reverse_N[i] = n % 10;
            n /= 10;
        }
    }

    // 转成16进制
    public static void toHex(int num) {
        if (num == 0) {
            System.out.println("0");
            return;
        }

        //定义一个对应关系表。
        char[] chs = {
                '0', '1', '2', '3',
                '4', '5', '6', '7',
                '8', '9', 'A', 'B',
                'C', 'D', 'E', 'F'};

        //定义一个数组,临时容器。
        char[] arr = new char[8];
        int pos = arr.length;
        while (num != 0) {
            int temp = num & 15;
            arr[--pos] = chs[temp];
            num = num >>> 4;
        }

        for (int x = pos; x < arr.length; x++) {
            System.out.print(arr[x]);
        }
    }

    //十进制-->十六进制。
    public static void toHex2(int num) {
        trans(num, 15, 4);
    }

    //十进制-->二进制。
    public static void toBinary(int num) {
        trans(num, 1, 1);
    }

    //十进制-->八进制。
    public static void toOctal(int num) {
        trans(num, 7, 3);
    }

    public static void trans(int num, int base, int offset) {
        if (num == 0) {
            System.out.println("0");
            return;
        }

        //定义一个对应关系表。
        char[] chs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        //定义一个数组。 临时容器。
        char[] arr = new char[32];
        int pos = arr.length;
        while (num != 0) {
            int temp = num & base;
            arr[--pos] = chs[temp];
            num = num >>> offset;
        }
        for (int x = pos; x < arr.length; x++) {
            System.out.print(arr[x]);
        }
    }

}
