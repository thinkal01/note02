package com.note.util.itcast;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * * 默认日期格式
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyyMMdd";

    public static final String DATE_PATTERN_1 = "yyyyMMddHHmmssSSS";

    public static final String MAIL_DATE_DT_PART_FORMAT = "yyyyMMdd";

    public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String SHORT_DATE_GBK_FORMAT = "yyyy年MM月dd日";

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分";

    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String LONG_DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒";

    public static final String MAIL_DATE_FORMAT = "yyyyMMddHHmmss";

    public static final String MAIL_DATE_HHMM_FORMAT = "HH:mm";

    public static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";

    public static final String FULL_DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";

    public static final String FULL_DATE_COMPACT_FORMAT = "yyyyMMddHHmmssSSS";

    public static final String LDAP_DATE_FORMAT = "yyyyMMddHHmm'Z'";

    public static final String US_LOCALE_DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";

    public static final String MAIL_DATE_HOUR_DT_PART_FORMAT = "yyyyMMddHH";

    public static final String MAIL_TIME_TM_PART_FORMAT = "HHmmss";

    public static final String LONG_DATE_TM_PART_FORMAT = "HH:mm:ss";

    public static final String Long_DATE_TM_PART_GBK_FORMAT = "HH时mm分ss秒";

    public static final String MAIL_DATA_DTM_PART_FORMAT = "MM月dd日HH:mm";

    public static final String POINT_DATA_DTM_PART_FORMAT = "yyyy.MM.dd";

    public static long NANO_ONE_SECOND = 1000;

    public static long NANO_ONE_MINUTE = 60 * NANO_ONE_SECOND;

    public static long NANO_ONE_HOUR = 60 * NANO_ONE_MINUTE;

    public static long NANO_ONE_DAY = 24 * NANO_ONE_HOUR;

    /**
     * * 返回日期字符串
     * *
     * * @param date
     * * @param format
     * * @return
     */


    public static String date2String(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format == null ? DEFAULT_DATE_PATTERN : format);
        return sdf.format(date);
    }

    /**
     * * 获取当前日期 ，格式类型yyyyMMdd
     */
    public static String getCurrentDate() {
        return toMailDateDtPartString(getNow());
    }

    /**
     * * 将一个日期型转换为'yyyyMMdd'格式字串
     * * @param aDate
     * * @return
     */
    public static final String toMailDateDtPartString(Date aDate) {
        return toFormatDateString(aDate, MAIL_DATE_DT_PART_FORMAT);
    }

    /**
     * * 将一个日期型转换为指定格式字串
     * * @param aDate
     * * @param formatStr
     * * @return
     */


    public static final String toFormatDateString(Date aDate, String formatStr) {
        if (aDate == null) return StringUtils.EMPTY;
        return new SimpleDateFormat(formatStr).format(aDate);

    }

    /**
     * * 获取当前日期类型时间
     */


    public static Date getNow() {
        return new Date();
    }

    /**
     * * 返回前一天
     * *
     * * @param date
     * * @return
     */


    public static Date getLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * * 字符串转换成Date
     * *
     * * @param date
     * * @param format
     * * @return
     */


    public static Date string2Date(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format == null ? DEFAULT_DATE_PATTERN : format);
        return sdf.parse(date);
    }

    /**
     * * 日期加一天
     * *
     * * @param date String格式
     * * @return String格式的时间
     */


    public static String getNextDay(String date) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(string2Date(date, DEFAULT_DATE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DATE, 1); //加1天
        Date nextDay = cal.getTime();
        return date2String(nextDay, DEFAULT_DATE_PATTERN);
    }

    /**
     * * 返回前几天
     * *
     * * @param date
     * * @return
     */


    public static Date getLastDays(Date date, int m) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, m);
        date = calendar.getTime();
        return date;
    }


    /**
     * * 获取当前日期和时间，格式yyyyMMddHHmmss
     * * @return
     */


    public static String getCurrentDateTime() {
        return toMailDateString(getNow());
    }

    /**
     * * 将一个日期型转换为'yyyyMMddHHmmss'格式字串
     * * @param aDate
     * * @return
     */


    public static final String toMailDateString(Date aDate) {
        return toFormatDateString(aDate, MAIL_DATE_FORMAT);
    }


    /**
     * * 计算两个日期之间相差的天数
     * * @param startDateStr yyyymmdd
     * * @param endDateStr yyyymmdd
     * * @return
     */


    public static final int getDifferenceDays2(String startDateStr, String endDateStr) {
        return new Long(getDifferenceMillis(startDateStr, endDateStr, MAIL_DATE_DT_PART_FORMAT) / (NANO_ONE_DAY)).intValue();
    }


    public static final long getDifferenceMillis(String startDateStr, String endDateStr, String dateFormat) {
        try {
            return getDifferenceMillis(parser(startDateStr, dateFormat), parser(endDateStr, dateFormat));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public static final Date parser(String aDateStr, String formatter) throws ParseException {
        if (StringUtils.isBlank(aDateStr)) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.parse(aDateStr);

    }


    public static final long getDifferenceMillis(String startDateStr, String endDateStr) {
        return getDifferenceMillis(startDateStr, endDateStr, SHORT_DATE_FORMAT);
    }


    public static final long getDifferenceMillis(Date startDate, Date endDate) {
        return Math.abs(endDate.getTime() - startDate.getTime());
    }

    /**
     * * 为一个日期加上指定天数
     * * @param aDate yyyyMMdd格式字串
     * * @param amount 天数
     * * @return
     */


    public static final String addDays(String aDate, int amount) {
        try {
            return DateUtil.toMailDateDtPartString(addTime(DateUtil.parseMailDateDtPartString(aDate), Calendar.DAY_OF_YEAR, amount));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * * 将一个符合'yyyyMMdd'格式的字串解析成日期型
     * * @param aDateStr
     * * @return
     */


    public static final Date parseMailDateDtPartString(String aDateStr) throws ParseException {
        return parser(aDateStr, MAIL_DATE_DT_PART_FORMAT);
    }


    private static final Date addTime(Date aDate, int timeType, int amount) {
        if (aDate == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.add(timeType, amount);
        return cal.getTime();

    }


    /*public static final Date aa() {
        String datetime = "20180523160200";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime ldt = LocalDateTime.parse(datetime, dtf);
        System.out.println(ldt);

        DateTimeFormatter fa = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String datetime2 = ldt.format(fa);
        System.out.println(datetime2);
    }*/
}