package com.note.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

public class NumberUtil {
    /**
     * 获取时间序列号
     *
     * @return
     */
    public static String getSerialNumber() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = format.format(new Date());
        dateStr += create6RandomNumber();
        return dateStr;
    }

    /**
     * 格式化为两位数字
     *
     * @param number
     * @return
     */
    public static String format2DoubleDigit(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String format = decimalFormat.format(number);
        return format;
    }

    /**
     * 生成6位随机数
     *
     * @return
     */
    public static String create6RandomNumber() {
        return createRandomNumber(6);
    }

    /**
     * 生成指定数量随机数
     *
     * @param count
     * @return
     */
    public static String createRandomNumber(int count) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            // 0（包括）和n（不包括）之间均匀分布的 int 值
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

    /**
     * BigDecimal 四舍五入
     *
     * @param bigDecimal
     * @param scale      保留小数位数
     * @return
     */
    public static BigDecimal roundBigDecimal(BigDecimal bigDecimal, int scale) {
        BigDecimal roundBigDecimal = bigDecimal.setScale(scale,
                BigDecimal.ROUND_HALF_UP);
        return roundBigDecimal;
    }

    /**
     * BigDecimal 四舍五入为整数
     *
     * @param bigDecimal
     * @return
     */
    public static int roundToInt(BigDecimal bigDecimal) {
        BigDecimal roundBigDecimal = roundBigDecimal(bigDecimal, 0);
        return roundBigDecimal.intValue();
    }

    /**
     * 四舍五入保留两位小数,补齐两位小数
     *
     * @return
     */
    public static String roundKeep2Decimal(Object obj) {
        return roundBigDecimal(new BigDecimal(obj.toString()), 2).toString();
    }

    /**
     * 判断是否为正整数
     *
     * @param obj
     * @return
     */
    public static boolean isPositiveInteger(Object obj) {
        Pattern pattern = Pattern.compile("^\\+?\\d*(\\.(0|00))?$");
        return pattern.matcher(obj.toString()).matches();
    }

    /**
     * 是否为整数
     *
     * @param bigDecimal
     * @return
     */
    public static boolean isInteger(BigDecimal bigDecimal) {
        if (new BigDecimal(bigDecimal.intValue()).compareTo(bigDecimal) == 0) {
            return true;
        }
        return false;
    }

    public static boolean isPositive2Decimal(Object obj) {
        Pattern pattern = Pattern.compile("^\\+?\\d*(\\.\\d{1,2})?$");
        return pattern.matcher(obj.toString()).matches();
    }

    /**
     * 获取小数部分
     *
     * @param bigDecimal
     * @return
     */
    public static BigDecimal getDecimalPart(BigDecimal bigDecimal) {
        return bigDecimal.subtract(new BigDecimal(bigDecimal.intValue()));
    }

    /**
     * 获取min-max范围随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static int randomBetween(int min, int max) {
        Random random = new Random();
        int s = 0;
        if (max > 0 && min <= max) {
            if (min < 0) {
                min = 1;
            }
            s = random.nextInt(max - min + 1) + min;
        }

        return s;
    }

    /**
     * 获取n个值的最大值
     *
     * @param tArr
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T max(T... tArr) {
        if (tArr.length < 1) {
            return null;
        }

        T max = tArr[0];
        for (T t : tArr) {
            if (t.compareTo(max) > 0) {
                max = t;
            }
        }

        return max;
    }

    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间毫秒值
        long millis = System.currentTimeMillis();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);
        return str;
    }

    /**
     * 商品id生成
     */
    public static long genItemId() {
        //取当前时间毫秒值
        long millis = System.currentTimeMillis();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }

}