package com.note.base;

import java.math.BigInteger;

public class BigInteger01 {
    public void test01() {
        // 通过大整数来创建对象
        BigInteger bi = new BigInteger("2147483648");

        BigInteger bi1 = new BigInteger("100");
        BigInteger bi2 = new BigInteger("50");

        System.out.println("add:" + bi1.add(bi2));
        System.out.println("subtract:" + bi1.subtract(bi2));
        System.out.println("multiply:" + bi1.multiply(bi2));
        System.out.println("divide:" + bi1.divide(bi2));

        // 返回商和余数的数组
        BigInteger[] bis = bi1.divideAndRemainder(bi2);
        System.out.println("商：" + bis[0]);
        System.out.println("余数：" + bis[1]);
    }
}
