package com.note.base;

import org.junit.Test;

import java.util.Scanner;

public class String01 {
    @Test
    public void test01() {
        // 会创建2个对象
        String s1 = new String("hello");
        // 创建1个对象
        String s2 = "hello";

        // 字符串如果是变量相加，先开空间，在拼接
        // 字符串如果是常量相加，是先加，然后在常量池找，如果有就直接返回，否则，就创建
        s1 = "hello";
        s2 = "world";
        String s3 = "helloworld";

        System.out.println(s3 == s1 + s2);// false
        // 通过反编译看源码
        // System.out.println(s3 == "helloworld");
        System.out.println(s3 == "hello" + "world");// true
    }

    public void testLogin() {
        String username = "admin";
        String password = "admin";

        for (int x = 0; x < 3; x++) {
            // 键盘录入用户名和密码。
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入用户名：");
            String name = sc.nextLine();
            System.out.println("请输入密码：");
            String pwd = sc.nextLine();

            // 比较用户名和密码。
            if (name.equals(username) && pwd.equals(password)) {
                // 如果都相同，则登录成功
                System.out.println("登录成功");
                break;
            } else {
                if ((2 - x) == 0) {
                    System.out.println("帐号被锁定，请与班长联系");
                } else {
                    System.out.println("登录失败，你还有" + (2 - x) + "次机会");
                }
            }
        }
    }

    public void testGuessNumber() {
        // 产生一个随机数
        int number = (int) (Math.random() * 100) + 1;

        while (true) {
            // 键盘录入数据
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入你要猜的数据(1-100)：");
            int guessNumber = sc.nextInt();

            // 判断
            if (guessNumber > number) {
                System.out.println("你猜的数据" + guessNumber + "大了");
            } else if (guessNumber < number) {
                System.out.println("你猜的数据" + guessNumber + "小了");
            } else {
                System.out.println("恭喜你，猜中了");
                break;
            }
        }
    }

    public void testCount() {
        String s = "Hello123World";

        //定义三个统计变量
        int bigCount = 0;
        int smallCount = 0;
        int numberCount = 0;

        for (int x = 0; x < s.length(); x++) {
            char ch = s.charAt(x);

            //判断该字符到底是属于那种类型的
            if (ch >= 'a' && ch <= 'z') {
                smallCount++;
            } else if (ch >= 'A' && ch <= 'Z') {
                bigCount++;
            } else if (ch >= '0' && ch <= '9') {
                numberCount++;
            }
        }

        System.out.println("大写字母" + bigCount + "个");
        System.out.println("小写字母" + smallCount + "个");
        System.out.println("数字" + numberCount + "个");
    }

    // 首字母大写
    public void testUpperCaseFirstChar() {
        // 定义一个字符串
        String s = "helloWORLD";
        // 链式编程
        String result = s.substring(0, 1).toUpperCase().concat(s.substring(1).toLowerCase());
    }

    // 数组转字符串
    public static String arrayToString(int[] arr) {
        String s = "";
        s += "[";

        for (int x = 0; x < arr.length; x++) {
            if (x == arr.length - 1) {
                s += arr[x];
                s += "]";
            } else {
                s += arr[x];
                s += ", ";
            }
        }

        return s;
    }

    // 用StringBuffer做拼接的方式
    public static String arrayToString2(int[] arr) {
        StringBuffer sb = new StringBuffer();

        sb.append("[");
        for (int x = 0; x < arr.length; x++) {
            if (x == arr.length - 1) {
                sb.append(arr[x]);
            } else {
                sb.append(arr[x]).append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }

    public static void getCountTest() {
        // 定义大串
        String maxString = "woaijavawozhenaijavawozhendeaijavawozhendehenaijavaxinbuxinwoaijavagun";
        // 定义小串
        String minString = "java";

        // 写功能实现
        int count = getCount(maxString, minString);
        System.out.println("Java在大串中出现了：" + count + "次");
    }

    public static int getCount(String maxString, String minString) {
        int count = 0;
        int index;

        //先查，赋值，判断
        while ((index = maxString.indexOf(minString)) != -1) {
            count++;
            maxString = maxString.substring(index + minString.length());
        }

        return count;
    }

    // 字符串反转
    public static String myReverse(String s) {
        String result = "";
        char[] chs = s.toCharArray();

        // 倒着遍历字符串
        for (int x = chs.length - 1; x >= 0; x--) {
            result += chs[x];
        }

        return result;
    }

    // 用StringBuffer的reverse()功能
    public static String myReverse2(String s) {
        return new StringBuffer(s).reverse().toString();
    }

    // 回文字符串
    public static boolean isSame2(String s) {
        return new StringBuffer(s).reverse().toString().equals(s);
    }

    public static boolean isSame(String s) {
        boolean flag = true;
        // 把字符串转成字符数组
        char[] chs = s.toCharArray();

        for (int start = 0, end = chs.length - 1; start <= end; start++, end--) {
            if (chs[start] != chs[end]) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    @Test
    public void substring01() {
        // beginIndex==endIndex 时返回""
        String substring = "abc".substring(0, 0);
    }

    @Test
    public void testNull() {
        String nullStr = null + ""; // "null"
        nullStr = "123" + null; // "123null"
    }

}
