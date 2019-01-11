package com.note.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHandler {
    public static int openDay = 5;
    private String iDate = "";
    private int iYear;
    private int iMonth;
    private int iDay;

    public DateHandler() {
    }

    //  iDateTime = 2002-01-01 23:23:23
    public void setDate(String iDateTime) {
        this.iDate = getDate(iDateTime);
    }


    public String getDate() {
        return this.iDate;
    }

    public int getYear() {
        iYear = getYear(iDate);
        return iYear;
    }


    public int getMonths() {
        iMonth = getMonths(iDate);
        return iMonth;
    }


    public int getDay() {
        iDay = getDay(iDate);
        return iDay;
    }

    public static String getDate(String dateTime) {
        return dateTime.substring(0, 10);
    }

    public static int getDay(String date) {
        return Integer.parseInt(date.substring(8, 10));
    }

    public static int getMonths(String date) {
        return Integer.parseInt(date.substring(5, 7));
    }


    public static int getYear(String date) {
        return Integer.parseInt(date.substring(0, 4));
    }

    /**
     * 计算是否是季度末
     *
     * @param date
     * @return
     */
    public static boolean isSeason(String date) {
        int getMonth = getMonths(date);
        boolean sign = false;
        if (getMonth == 3)
            sign = true;
        if (getMonth == 6)
            sign = true;
        if (getMonth == 9)
            sign = true;
        if (getMonth == 12)
            sign = true;
        return sign;
    }

    public static final int getWeekNum(String strWeek) {
        int returnValue = 0;

        if (strWeek.equals("Mon")) {
            returnValue = 1;
        } else if (strWeek.equals("Tue")) {
            returnValue = 2;
        } else if (strWeek.equals("Wed")) {
            returnValue = 3;
        } else if (strWeek.equals("Thu")) {
            returnValue = 4;
        } else if (strWeek.equals("Fri")) {
            returnValue = 5;
        } else if (strWeek.equals("Sat")) {
            returnValue = 6;
        } else if (strWeek.equals("Sun")) {
            returnValue = 0;
        }

        return returnValue;
    }

    /**
     * 带格式
     *
     * @param afterDay
     * @param pattern
     * @return
     */
    public static String getDateFromNow(int afterDay, String pattern) {
        // Calendar calendar = Calendar.getInstance();
        GregorianCalendar calendar = new GregorianCalendar();
        Date date = null;
        DateFormat df = new SimpleDateFormat(pattern);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + afterDay);
        date = calendar.getTime();

        return df.format(date);
    }

    /**
     * 计算从现在开始几天后的时间
     *
     * @param afterDay
     */
    public static String getDateFromNow(int afterDay) {
        return getDateFromNow(afterDay, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 得到当前时间，用于文件名，没有特殊字符，使用yyyyMMddHHmmss格式
     *
     * @param afterDay
     */
    public static String getNowForFileName(int afterDay) {
        return getDateFromNow(afterDay, "yyyyMMddHHmmss");
    }

    public static String getDates(Calendar calendar) {
        return String.valueOf(calendar.get(Calendar.YEAR)) + "-" + getMonths(calendar) + "-" + getDays(calendar);
    }

    public static String getMonths(Calendar calendar) {
        int intMon = calendar.get(Calendar.MONTH) + 1;
        String mons;

        if (intMon < 10) {
            mons = "0" + String.valueOf(intMon);
        } else {
            mons = String.valueOf(intMon);
        }

        return mons;
    }

    public static String getDays(Calendar calendar) {
        int intDay = calendar.get(Calendar.DAY_OF_MONTH);
        String days;

        if (intDay < 10) {
            days = "0" + String.valueOf(intDay);
        } else {
            days = String.valueOf(intDay);
        }

        return days;
    }

    /**
     * 获取今天的日期字符串
     */
    public static String getTodays() {
        Calendar calendar = Calendar.getInstance();
        return getDates(calendar);
    }

    /**
     * 获取昨天的日期的字符串
     */
    public static String getYesterday() {
        Calendar cld = Calendar.getInstance();
        cld.add(Calendar.DATE, -1);
        return getDates(cld);
    }


    /**
     * 获取当前月份
     *
     * @return 返回字符串 格式：两位数
     */
    public static String getCurrentMonth() {
        Calendar cld = Calendar.getInstance();
        return getMonths(cld);
    }

}
