package com.note.base.java8;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test02 {
    @Test
    public void test01() {
        // 二进制字面量
        int x = 0b100101;
        // 数字字面量可以出现下划线
        int y = 1_1123_1000;
        // 不能出现在数值开头和结尾
        int a = 0x11_22;
        // 不能出现在进制标识和数值之间
        int z = 0x111_222;
        // 不能出现在小数点旁边
        double d = 12.3_4;
    }

    @Test
    public void test02() {
        // try-with-resources 语句
        // try(必须是java.lang.AutoCloseable的子类对象){…}
        try (FileReader fr = new FileReader("a.txt");
             FileWriter fw = new FileWriter("b.txt")) {
            int ch;
            while ((ch = fr.read()) != -1) {
                fw.write(ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testException() {
        int a = 10;
        int b = 0;
        int[] arr = {1, 2, 3};

        // JDK7的处理方案
        try {
            System.out.println(a / b);
            System.out.println(arr[3]);
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
            System.out.println("出问题了");
        }
    }


}
