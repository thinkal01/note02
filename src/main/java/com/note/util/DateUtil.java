package com.note.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: 时间和日期的工具类</p>
 * <p>Description: DateUtil类包含了标准的时间和日期格式，以及这些格式在字符串及日期之间转换的方法</p>
 */
public class DateUtil {
    private static final String PATTERN_DATE = "yyyy-MM-dd";
    private static final String PATTERN_TIME = "HH:mm:ss";
    private static final String PATTERN_DATETIME = PATTERN_DATE + " " + PATTERN_TIME;

    /**
     * 日期转字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String date2Str(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(date);
        return format;
    }

    /**
     * 将日期转换为yyyy-MM-dd字符串
     */
    public static String date2DayStr(Date date) {
        return date2Str(date, PATTERN_DATE);
    }

    /**
     * 将日期转换为yyyy-MM-dd HH:mm:ss字符串
     */
    public static String date2SecondStr(Date date) {
        return date2Str(date, PATTERN_DATETIME);
    }

    /**
     * 字符串转日期
     *
     * @param strDate
     * @param pattern
     * @return
     */
    public static Date str2Date(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date date;

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            throw new RuntimeException(strDate + "转日期失败,请检查日期格式");
        }

        return date;
    }

    /**
     * 字符串转日期时间
     *
     * @param datetime
     * @return
     */
    public static Date str2DateTime(String datetime) {
        return str2Date(datetime, PATTERN_DATETIME);
    }

    /**
     * 字符串转日期时间
     *
     * @param date
     * @param time
     * @return
     */
    public static Date str2DateTime(String date, String time) {
        return str2DateTime(date + " " + time);
    }

    /**
     * 时间转日期
     *
     * @param time
     * @return
     */
    public static Date str2DateByTime(String time) {
        return str2DateTime(getNowDayStr(), time);
    }

    /**
     * 时间转日期
     *
     * @param time
     * @return
     */
    public static Date str2DateTime(Date Date, String time) {
        return str2DateTime(date2DayStr(Date), time);
    }

    /**
     * 获取日期中的天
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 校验天有效性
     *
     * @param day
     * @return
     */
    public static boolean checkDay(String day) {
        int dayInt = Integer.parseInt(day);
        if (dayInt > 0 && dayInt < 32) {
            return true;
        }

        return false;
    }

    /**
     * 获取当前时间字符串
     *
     * @return
     */
    public static String getNowDayStr() {
        return getNowStr(PATTERN_DATE);
    }

    /**
     * 获取当前时间字符串
     *
     * @return
     */
    public static String getNowStr(String pattern) {
        return date2Str(new Date(), pattern);
    }

    /**
     * 获取当前月初时间
     */
    public static Date beginOfThisMonth() {
        return dayOfThisMonth(1);
    }

    /**
     * 获取当月某天时间字符串
     *
     * @param obj
     * @return
     */
    public static Date dayOfThisMonth(Object obj) {
        int day = Integer.parseInt(obj.toString());
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        return dayOfMonth(year, month, day);
    }

    /**
     * 获取上月某天时间字符串
     *
     * @param obj
     * @return
     */
    public static Date dayOfPreMonth(Object obj) {
        int day = Integer.parseInt(obj.toString());
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) - 1;
        return dayOfMonth(year, month, day);
    }

    /**
     * 获取下月某天时间字符串
     *
     * @param day
     * @return
     */
    public static Date dayOfNextMonth(int day) {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        return dayOfMonth(year, month, day);
    }

    /**
     * 获取今天零点
     */
    public static Date zeroOfToday() {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth(year, month, day);
    }

    /**
     * 获取某天零点
     */
    public static Date zeroOfDate(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth(year, month, day);
    }

    /**
     * 获取某天零点
     *
     * @param date
     * @return
     */
    public static Date getZeroByCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date zero = calendar.getTime();
        return zero;
    }

    /**
     * 获取某天零点
     *
     * @param date
     * @return
     */
    public static Date getZeroByFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        Date parse = null;

        try {
            parse = sdf.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parse;
    }

    /**
     * 判断时间是否在今天零点之前
     *
     * @param date
     * @return
     */
    public static boolean isLessThanToday(Date date) {
        Date zeroOfToday = zeroOfToday();
        return date.compareTo(zeroOfToday) < 0;
    }

    public static Date dayOfMonth(int year, int month, int day) {
        Calendar instance = Calendar.getInstance();
        instance.clear();
        instance.set(year, month, day, 0, 0, 0);
        Date time = instance.getTime();
        return time;
    }

    //判断日期是否是今天
    public static boolean isToday(Date date) {
        return isThisTime(date, PATTERN_DATE);
    }

    //判断选择的日期是否是本周
    public static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        if (paramWeek == currentWeek) {
            return true;
        }

        return false;
    }

    //判断日期是否是本月
    public static boolean isThisMonth(Date date) {
        return isThisTime(date, "yyyy-MM");
    }

    /**
     * 是否与当前格式时间相等
     *
     * @param date
     * @param pattern
     * @return
     */
    private static boolean isThisTime(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }

        return false;
    }

    /**
     * date2比date1多的天数,按天计算
     *
     * @param date1
     * @param date2
     */
    public static int diffDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        if (year1 != year2) //不同年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) //闰年
                {
                    timeDistance += 366;
                } else //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else //同一年
        {
            return day2 - day1;
        }
    }

    /**
     * 计算两个日期间相隔的日子,按小时计算(有问题?)
     *
     * @param dates1 格式:2005-06-20
     * @param dates2 格式:2005-06-21
     */
    public static int diffDate(String dates1, String dates2) {
        String[] tt = dates1.split("-");
        Date date1 = new Date(Integer.parseInt(tt[0])-1900, Integer.parseInt(tt[1]) - 1, Integer.parseInt(tt[2]));
        // GregorianCalendar firstCalendar = new GregorianCalendar(Integer.parseInt(tt[0]) + 1900, Integer.parseInt(tt[1]) - 1, Integer.parseInt(tt[2]));
        // GregorianCalendar firstCalendar = new GregorianCalendar(Integer.parseInt(tt[0]) , Integer.parseInt(tt[1]) - 1, Integer.parseInt(tt[2]));

        tt = dates2.split("-");
        Date date2 = new Date(Integer.parseInt(tt[0])-1900, Integer.parseInt(tt[1]) - 1, Integer.parseInt(tt[2]));
        // GregorianCalendar nextCalendar = new GregorianCalendar(Integer.parseInt(tt[0]) + 1900, Integer.parseInt(tt[1]) - 1, Integer.parseInt(tt[2]));
        // GregorianCalendar nextCalendar = new GregorianCalendar(Integer.parseInt(tt[0]) , Integer.parseInt(tt[1]) - 1, Integer.parseInt(tt[2]));
        // return (int) (nextCalendar.getTimeInMillis() - firstCalendar.getTimeInMillis()) / (24 * 60 * 60 * 1000);
        long l = date2.getTime() - date1.getTime();
        return (int) l / (24 * 60 * 60 * 1000);
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔(相差天数)
     *
     * @param date1
     * @param date2
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        // 时间规整
        Date date3 = getZeroByCalendar(date1);
        Date date4 = getZeroByCalendar(date2);
        int days = (int) ((date4.getTime() - date3.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    public static long randomBetween(long begin, long end) {
        // 随机获取前40%
        double coefficient = 0.4;
        int flag = 0;
        long rtn;

        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        // 死循环
        //		if (rtn == begin || rtn == end) {
        //			return random(begin, end);
        //		}

        do {
            rtn = begin + (long) (Math.random() * coefficient * (end - begin));
            ++flag;
        } while ((rtn == begin || rtn == end) && flag <= 20);
        return rtn;
    }

    /**
     * 获取MMyy类型信用卡有效期
     *
     * @param date
     * @return
     */
    public static String getExpdate(String date) {
        Date parseDate = null;

        try {
            parseDate = new SimpleDateFormat("yyyy-MM").parse(date);
        } catch (ParseException e) {
            new RuntimeException(date + "日期转换失败");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMyy");
        return simpleDateFormat.format(parseDate);
    }

    /**
     * 账单日-还款日是否跨月
     *
     * @param billObj
     * @param repayObj
     * @return
     */
    public static boolean isBillCrossMonth(Object billObj, Object repayObj) {
        int billDay = Integer.parseInt(billObj.toString());
        int repayDay = Integer.parseInt(repayObj.toString());
        return billDay > repayDay;
    }

    /**
     * 获取本月有效账单日和还款日
     *
     * @param billDay
     * @param repayDay
     * @return
     */
    public static Map<String, Date> getBillAndRepayDate(int billDay, int repayDay) {
        Map<String, Date> dateMap = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        int dayOfMonth = instance.get(Calendar.DAY_OF_MONTH);
        Date billDate = null;
        Date repayDate = null;

        // 账单日小于还款日,则没有跨月
        if (billDay < repayDay) {
            // 今天在账单日-还款日期间
            if (dayOfMonth >= billDay && dayOfMonth <= repayDay) {
                billDate = dayOfThisMonth(billDay);
                repayDate = dayOfThisMonth(repayDay);
            }
        } else {
            if (dayOfMonth >= billDay || dayOfMonth <= repayDay) {
                if (dayOfMonth >= billDay) {
                    billDate = dayOfThisMonth(billDay);
                    repayDate = dayOfNextMonth(repayDay);
                } else {
                    billDate = dayOfPreMonth(billDay);
                    repayDate = dayOfThisMonth(repayDay);
                }
            }
        }

        dateMap.put("billDate", billDate);
        dateMap.put("repayDate", repayDate);
        return dateMap;
    }

    /**
     * 获取本月有效账单日和还款日
     *
     * @param billDay
     * @param repayDay
     * @return
     */
    public static Map<String, Date> getBillAndRepayDate(String billDay, String repayDay) {
        int billDayInt = Integer.parseInt(billDay);
        int repayDayInt = Integer.parseInt(repayDay);
        return getBillAndRepayDate(billDayInt, repayDayInt);
    }

    /**
     * 校验信用卡字符串型有效期
     *
     * @return
     */
    public static boolean checkCreditcardExpDate(String expDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            Date date = sdf.parse(expDate);
            return checkCreditcardExpDate(date);
        } catch (ParseException e) {
            throw new RuntimeException(expDate + "转日期失败");
        }
    }

    /**
     * 校验信用卡有效期
     *
     * @return
     */
    public static boolean checkCreditcardExpDate(Date expDate) {
        Calendar expCalendar = Calendar.getInstance();
        expCalendar.setTime(expDate);
        int expYear = expCalendar.get(Calendar.YEAR);

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        if (year < expYear) {
            return true;
        } else if (year == expYear) {
            int expMonth = expCalendar.get(Calendar.MONTH);
            int month = now.get(Calendar.MONTH);
            if (expMonth < month) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * 指定日期加上天数后的日期
     *
     * @param date 时间
     * @param num  增加的天数
     */
    public String plusDay(String date, int num) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endDate = null;

        try {
            Date currDate = format.parse(date);
            Calendar ca = Calendar.getInstance();
            ca.setTime(currDate);
            ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的

            currDate = ca.getTime();
            endDate = format.format(currDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return endDate;
    }
}
