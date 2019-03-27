package com.note.base.java8.joda;


import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class JodaTest2 {

    public static void main(String[] args) {
        DateTime today = new DateTime();
        DateTime tomorrow = today.plusDays(1);
        System.out.println(today.toString("yyyy-MM-dd"));
        System.out.println(tomorrow.toString("yyyy-MM-dd"));

        DateTime d1 = today.withDayOfMonth(1);
        System.out.println(d1.toString("yyyy-MM-dd"));

        LocalDate localDate = new LocalDate();
        System.out.println(localDate);

        localDate = localDate.plusMonths(3).dayOfMonth().withMinimumValue();

        DateTime dateTime = new DateTime();
        // 计算2年前第3个月最后1天的日期
        DateTime dateTime2 = dateTime.minusYears(2).monthOfYear().setCopy(3).dayOfMonth().withMinimumValue();
        System.out.println(dateTime2.toString("yyyy-MM-dd"));
    }

}
