package com.note.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Pattern;

public class StrUtil {
    /**
     * 在数组中查找是否存在
     *
     * @param a
     * @param key
     * @return
     */
    public static int binarySearch(Object[] a, Object key) {
        if (a == null || a.length == 0 || key == null) {
            return -1;
        }

        return Arrays.binarySearch(a, key);
    }

    /**
     * 计算字符串长度，基于中文编码的情况下，即中文算2个字符
     *
     * @param s
     * @return
     */
    public static int strlen(String s) {
        if (s == null) {
            return 0;
        }

        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
    }

    /**
     * 判断字符串是否为null或空白字符
     *
     * @param s 需要判断的字符串
     * @return
     */
    public static boolean isEmpty(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        return false;
    }

    /**
     * 判断字符串是否为null或去掉空白后是否为空白字符
     *
     * @param s 需要判断的字符串
     * @return
     */
    public static boolean isEmptyTrim(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        }

        return false;
    }

    /**
     * 是否全部为数字
     *
     * @param s
     * @return
     */
    public static boolean isNumeric(String s) {
        if (isEmptyTrim(s)) return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    // 是否为数字
    public static boolean isnum(String s) {
        char[] ch = s.toCharArray();
        for (char c : ch) {
            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }

    /**
     * 数字验证
     *
     * @param val
     * @return 提取的数字。
     */
    public static boolean isNum(String val) {
        return val == null || "".equals(val) ? false : val.matches("^[0-9]*$");
    }

    /**
     * 是否为整数或浮点数
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return Pattern.matches("^(-?\\d+)(\\.\\d+)?$", str);
    }

    public static boolean isEmptyCollection(Collection<?> paraList) {
        if (paraList == null || paraList.size() == 0) {
            return true;
        }

        return false;
    }

    /**
     * @param trandt   需要增减的日期 yyyymmdd字符串格式
     * @param increase 增减额
     * @param part     增减部位：y年 m月 d日
     * @return
     */
    public static String dateadd(String trandt, int increase, String part) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            Date d = df.parse(trandt);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            if ("d".equalsIgnoreCase(part)) {
                c.add(Calendar.DATE, increase);
            } else if ("m".equalsIgnoreCase(part)) {
                c.add(Calendar.MONTH, increase);
            } else if ("y".equalsIgnoreCase(part)) {
                c.add(Calendar.YEAR, increase);
            } else {
                MsgUtil.getMsg("日期{0}，增量{1}，类型{2} 处理异常", new String[]{trandt, increase + "", part});
            }

            return df.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }


    public static String ltrim(String str) {
        int len = str.toCharArray().length;
        int st = 0;
        int off = 0;
        char[] val = str.toCharArray();

        while ((st < len) && (val[off + st] <= ' ')) {
            st++;
        }

        return (st > 0) ? str.substring(st, len) : str;
    }

    /*
    在java中，字符串“abcd”与字符串“ab你好”的长度是一样，都是四个字符
    但对应的字节数不同，一个汉字占两个字节。
    定义一个方法，按照最大的字节数来取子串。
    如：对于“ab你好”，如果取三个字节，那么子串就是ab与“你”字的半个，
    那么半个就要舍弃。如果去四个字节就是“ab你”，取五个字节还是“ab你”.
    */
    public static String cutStrByUTF8(String str, int len) throws IOException {
        byte[] buf = str.getBytes("utf-8");
        int count = 0;
        for (int x = len - 1; x >= 0; x--) {
            if (buf[x] < 0)
                count++;
            else
                break;
        }

        if (count % 3 == 0)
            return new String(buf, 0, len, "utf-8");
        else if (count % 3 == 1)
            return new String(buf, 0, len - 1, "utf-8");
        else
            return new String(buf, 0, len - 2, "utf-8");
    }

    public static String cutStrByGBK(String str, int len) throws IOException {
        byte[] buf = str.getBytes("gbk");
        int count = 0;
        for (int x = len - 1; x >= 0; x--) {
            if (buf[x] < 0)
                count++;
            else
                break;
        }
        if (count % 2 == 0)
            return new String(buf, 0, len, "gbk");
        else
            return new String(buf, 0, len - 1, "gbk");
    }
}
