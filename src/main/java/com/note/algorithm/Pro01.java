package com.note.algorithm;

import java.util.ArrayList;

public class Pro01 {

    // 求0—7所能组成的奇数个数
    public void test01() {
        int count;
        //声明由数字组成的数
        int n = 8;
        //一位数
        count = n / 2;
        //两位数
        count += (n - 1) * n / 2;
        //三位数
        count += (n - 1) * n * n / 2;
        //四位数
        count += (n - 1) * n * n * n / 2;
        //五位数
        count += (n - 1) * n * n * n * n / 2;
        //六位数
        count += (n - 1) * n * n * n * n * n / 2;
        //七位数
        count += (n - 1) * n * n * n * n * n * n / 2;
        System.out.println("0-7所能组成的奇数个数：" + count);
    }

    public void test02() {
        // 一个偶数总能表示为两个素数之和
        int n = 19;
        for (int i = 2; i < n / 2 + 1; i++) {
            if (isPrime(i) && isPrime(n - i)) {
                System.out.println(n + "=" + (i) + "+" + (n - i));
                break;
            }
        }
    }

    // 判断素数
    public boolean isPrime(int n) {
        for (int i = 2; i < Math.sqrt(n); ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void printGraph() {
        /*
         *****
         ****
         ***
         **
         *
         */
        for (int x = 1; x <= 5; x++) {
            for (int y = x; y <= 5; y++) {
                System.out.print("*");
            }
            System.out.println();
        }

        /*
        * * * * *
        -* * * *
        --* * *
        ---* *
        ----*
        */
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = i; j < 5; j++) {
                System.out.print("* ");
            }
            System.out.print("\n");
        }
    }

}

// 题目：两个乒乓球队进行比赛，各出三人。甲队为a,b,c三人，乙队为x,y,z三人。已抽签决定比赛名单。
// 有人向队员打听比赛的名单。a说他不和x比，c说他不和x,z比，请编程序找出三队赛手的名单。
class Prog18 {
    String a, b, c;//甲队成员

    public static void main(String[] args) {
        String[] racer = {"x", "y", "z"};//乙队成员
        ArrayList<Prog18> arrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                for (int k = 0; k < 3; k++) {
                    Prog18 prog18 = new Prog18(racer[i], racer[j], racer[k]);
                    if (!prog18.a.equals(prog18.b) && !prog18.a.equals(prog18.c) && !prog18.b.equals(prog18.c) && !prog18.a.equals("x") && !prog18.c.equals("x") && !prog18.c.equals("z"))
                        arrayList.add(prog18);
                }
        for (Object obj : arrayList)
            System.out.println(obj);
    }

    //构造方法
    private Prog18(String a, String b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String toString() {
        return "a的对手是" + a + "  " + "b的对手是" + b + "  " + "c的对手是" + c;
    }
}