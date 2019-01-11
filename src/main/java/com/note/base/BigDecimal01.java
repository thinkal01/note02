package com.note.base;

import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimal01 {

    @Test
    public void intValue() {
        BigDecimal bigDecimal = new BigDecimal("100.60");
        int i = bigDecimal.intValue(); // 100
    }

    @Test
    public void test01() {
        // 初始化小数时，尽量用字符串形式
        BigDecimal bigFloat = new BigDecimal("0.1");

        // 比较大小时用compareTo方法，判断变量值是否为0，与BigDecimal.ZERO比较大小。
        bigFloat.compareTo(BigDecimal.ZERO);

        /*
         * setScale
         *  第一个参数,保留小数位数
         *  第二个参数,四舍五入模式
         *  类似 Math.floor,ceil
         * 四舍五入
         *  ROUND_HALF_DOWN: 向下取整,>0.5 进位
         *  ROUND_HALF_UP: 向上取整,>=0.5 进位
         *  ROUND_DOWN: 截断
         *  ROUND_UP: 非0进一位
         *  ROUND_CEILING: 正数做ROUND_UP,负数做ROUND_DOWN
         *  ROUND_FLOOR: 与上相反
         */
        BigDecimal a = new BigDecimal(1.5);
        System.out.println("down=" + a.setScale(0, BigDecimal.ROUND_HALF_DOWN));
        System.out.println("up=" + a.setScale(0, BigDecimal.ROUND_HALF_UP));

        // 2.0
        System.out.println(Math.ceil(1.01));
        // -1.0
        System.out.println(Math.ceil(-1.01));
        // 2.0
        System.out.println(Math.ceil(1.5));
        // -1.0
        System.out.println(Math.ceil(-1.5));
        // Math.abs(-2147483648) 结果 -2147483648,整型溢出
    }

    public void testDivide() {
        // 作除法时，除了要考虑除数是否为0，更要考虑是否能除尽的问题
        BigDecimal num = new BigDecimal("1");
        // 保留两位小数,向下舍入
        num.divide(new BigDecimal("3"), 2, BigDecimal.ROUND_HALF_DOWN);

        BigDecimal amt = new BigDecimal(1001);
        // 余数
        BigDecimal remainder = amt.remainder(BigDecimal.valueOf(20));

        BigDecimal[] results = amt.divideAndRemainder(BigDecimal.valueOf(20));
        // 商
        System.out.println(results[0]);
        // 余数
        System.out.println(results[1]);
    }

    @Test
    public void compareAndEquals() {
        BigDecimal z1 = new BigDecimal("0");
        BigDecimal z2 = new BigDecimal("0.0");
        // equals方法会比较值和精确度，而compareTo则会忽略精度
        System.out.println(z1.equals(z2)); // false
        System.out.println(z1.compareTo(z2)); // 0
    }
}
