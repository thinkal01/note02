package com.note.base;

import org.junit.Test;

import java.util.Scanner;

public class Character01 {

    @Test
    public void test01() {
        Character ch = new Character((char) 97);
        ch = new Character('a');

        // 判断给定的字符是否是大写字符
        System.out.println("isUpperCase:" + Character.isUpperCase('A'));
        System.out.println("isUpperCase:" + Character.isUpperCase('a'));
        // false
        System.out.println("isUpperCase:" + Character.isUpperCase('0'));
        // 判断给定的字符是否是小写字符
        System.out.println("isLowerCase:" + Character.isLowerCase('A'));
        System.out.println("isLowerCase:" + Character.isLowerCase('a'));
        // 判断给定的字符是否是数字字符
        System.out.println("isDigit:" + Character.isDigit('a'));
        System.out.println("isDigit:" + Character.isDigit('0'));
        // 把给定的字符转换为大写字符
        System.out.println("toUpperCase:" + Character.toUpperCase('a'));
        // 把给定的字符转换为小写字符
        System.out.println("toLowerCase:" + Character.toLowerCase('A'));
    }

    public void countChar() {
        int bigCount = 0;
        int smallCount = 0;
        int numberCount = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String line = sc.nextLine();

        char[] chs = line.toCharArray();
        for (int x = 0; x < chs.length; x++) {
            char ch = chs[x];

            if (Character.isUpperCase(ch)) {
                bigCount++;
            } else if (Character.isLowerCase(ch)) {
                smallCount++;
            } else if (Character.isDigit(ch)) {
                numberCount++;
            }
        }

        System.out.println("大写字母：" + bigCount + "个");
        System.out.println("小写字母：" + smallCount + "个");
        System.out.println("数字字符：" + numberCount + "个");
    }
}
