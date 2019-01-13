package com.note.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDateUtil {

    /*
     DateFormat 和 SimpleDateFormat 类不都是线程安全的，在多线程环境下调用 format() 和 parse() 方法应该使用同步代码
     DateFormat中定义了一个protected属性的Calendar类的对象：calendar。因为Calendar概念复杂，牵扯到时区与本地化等，Jdk实现中使用了成员变量来传递参数，这就造成在多线程的时候会出现错误。
     */
    // 采用threadLocal
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static Date parse(String dateStr) throws ParseException {
        return threadLocal.get().parse(dateStr);
    }

    public static String format(Date date) {
        return threadLocal.get().format(date);
    }

    // 采用threadLocal2
    /*private static final String date_format = "yyyy-MM-dd HH:mm:ss";
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<>();

    public static DateFormat getDateFormat() {
        DateFormat df = threadLocal.get();
        if (df == null) {
            df = new SimpleDateFormat(date_format);
            threadLocal.set(df);
        }
        return df;
    }

    public static String formatDate(Date date) throws ParseException {
        return getDateFormat().format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }*/

    // 采用同步
    /*private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date) throws ParseException {
        synchronized (sdf) {
            return sdf.format(date);
        }
    }

    public static Date parse(String strDate) throws ParseException {
        synchronized (sdf) {
            return sdf.parse(strDate);
        }
    }*/
}