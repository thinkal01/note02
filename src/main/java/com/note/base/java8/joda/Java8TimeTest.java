package com.note.base.java8.joda;


import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.TreeSet;

public class Java8TimeTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getYear() + ", " + localDate.getMonthValue() + ", " + localDate.getDayOfMonth());

        LocalDate localDate2 = LocalDate.of(2017, 3, 3);
        System.out.println(localDate2);

        LocalDate localDate3 = LocalDate.of(2010, 3, 25);
        MonthDay monthDay = MonthDay.of(localDate3.getMonth(), localDate3.getDayOfMonth());
        MonthDay monthDay2 = MonthDay.from(LocalDate.of(2011, 3, 26));

        if (monthDay.equals(monthDay2)) {
            System.out.println("equals");
        } else {
            System.out.println("not equals");
        }

        LocalTime time = LocalTime.now();
        System.out.println(time);

        LocalTime time2 = time.plusHours(3).plusMinutes(20);
        System.out.println(time2);

        LocalDate localDate1 = localDate.plus(2, ChronoUnit.WEEKS);
        System.out.println(localDate1);

        LocalDate localDate4 = localDate.minus(2, ChronoUnit.MONTHS);
        System.out.println(localDate4);

        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());

        LocalDate localDate5 = LocalDate.now();
        LocalDate localDate6 = LocalDate.of(2017, 3, 18);

        System.out.println(localDate5.isAfter(localDate6));
        System.out.println(localDate5.isBefore(localDate6));
        System.out.println(localDate5.equals(localDate6));

        // 所有可用时区
        Set<String> set = ZoneId.getAvailableZoneIds();
        Set<String> treeSet = new TreeSet<String>() {
            {
                addAll(set);
            }
        };
        treeSet.stream().forEach(System.out::println);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        // 时区
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        System.out.println(zonedDateTime);

        // 年月
        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth);
        System.out.println(yearMonth.lengthOfMonth());
        System.out.println(yearMonth.isLeapYear());

        YearMonth yearMonth1 = YearMonth.of(2016, 2);
        // 月的天数
        System.out.println(yearMonth1.lengthOfMonth());
        // 年的天数
        System.out.println(yearMonth1.lengthOfYear());
        // 是否是闰年
        System.out.println(yearMonth1.isLeapYear());

        LocalDate localDate7 = LocalDate.now();
        LocalDate localDate8 = LocalDate.of(2018, 3, 16);
        // 时间间隔
        Period period = Period.between(localDate7, localDate8);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(Instant.now());
    }
}
