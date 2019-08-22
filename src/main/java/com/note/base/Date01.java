package com.note.base;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.note.util.DateUtil.diffDays;
import static com.note.util.DateUtil.differentDaysByMillisecond;

public class Date01 {
    // 日期格式化模版
    public void test03() throws ParseException {
		/*
		  	y     年
			M     年中的月份
			D     年中的天数
			d     月份中的天数
			H     一天中的小时数（0-23）
			h     am/pm 中的小时数（1-12）
			m     小时中的分钟数
			s     分钟中的秒数
			S     毫秒数
		 */
        //利用构造函数设置格式化模板
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        //执行格式化功能
        System.out.println(formatter.format(date));
        //设置格式化模板
        formatter.applyPattern("yyyy-MM-dd");
        System.out.println(formatter.format(date));

        // 1970 01 01
        Date parse = new SimpleDateFormat("HH:mm:ss").parse("22:00:00");
        // 00:00:00
        Date parse1 = new SimpleDateFormat("yyyy-MM-dd").parse("2017-12-15");
    }

    // 获取毫秒值
    @Test
    public void test05() {
        // 当前时间,单位都是毫秒
        long time = new Date().getTime();
        long currentTimeMillis = System.currentTimeMillis();
        GregorianCalendar calendar = new GregorianCalendar();
        long timeInMillis = calendar.getTimeInMillis();
    }

    // 日期比较大小
    @Test
    public void test02() {
        Date date1 = new Date();
        Date date2 = new Date();
        // 时间比较大小
        int compareTo = date1.compareTo(date2);

        Calendar instance = Calendar.getInstance();
//        instance.compareTo(anotherCalendar);
    }

    // 日期差距
    @Test
    public void test01() {
        String dateStr = "2008-1-1 1:21:28";
        String dateStr2 = "2010-1-2 1:21:28";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date2 = format.parse(dateStr2);
            Date date = format.parse(dateStr);

            System.out.println("两个日期的差距：" + diffDays(date, date2));
            System.out.println("两个日期的差距：" + differentDaysByMillisecond(date, date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // 获取任意一年的二月有多少天
    public void testCalendar() {
        // 键盘录入任意的年份
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入年份：");
        int year = sc.nextInt();

        Calendar c = Calendar.getInstance();
        // 其实是这一年的3月1日
        c.set(year, 2, 1);
        // 把时间往前推一天，就是2月的最后一天
        c.add(Calendar.DATE, -1);

        System.out.println(c.get(Calendar.DATE));
    }

    @Test
    public void testDataFormat() {
        DateFormat df1 = DateFormat.getDateInstance();    // 得到日期的DateFormat对象
        DateFormat df2 = DateFormat.getDateTimeInstance();
        df1 = DateFormat.getDateInstance(DateFormat.YEAR_FIELD, new Locale("zh", "CN"));
        df2 = DateFormat.getDateTimeInstance(DateFormat.YEAR_FIELD, DateFormat.ERA_FIELD, new Locale("zh", "CN"));
        System.out.println("DATE：" + df1.format(new Date())); // 按照日期格式化
        System.out.println("DATETIME：" + df2.format(new Date()));
    }

    @Test
    public void testFormatException() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.parse("2018-07-01");
    }
}
