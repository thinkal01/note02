package com.note.base.java8;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Test01 {
    public void test01() {
        // 可以引用外部非final变量,不能修改外部变量值
        int i = 0;
        Runnable r = () -> {
            System.out.println("i=" + i);
        };
    }

    public void test02() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("a", Person.Sex.FEMALE, 23, 1.42));
        personList.add(new Person("b", Person.Sex.MALE, 34, 1.44));
        personList.add(new Person("c", Person.Sex.FEMALE, 33, 1.52));
        personList.add(new Person("d", Person.Sex.FEMALE, 65, 1.72));

        Stream<Person> stream = personList.stream();
        stream.filter(person -> person.getGender() == Person.Sex.FEMALE).forEach(person -> System.out.println(person));
        double avgHeight = stream.filter(person -> person.getGender() == Person.Sex.FEMALE)
                .mapToDouble(person -> person.getHeight())
                .average().getAsDouble();
    }

    @Test
    public void test03() {
        LocalDate now = LocalDate.now();
        System.out.println(now.getYear());
        // 从1开始
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());
        // yyyy-MM-dd
        System.out.println(now);
    }

    @Test
    public void test04() {
        LocalTime now = LocalTime.now();
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
        // 22:32:08.016
        System.out.println(now);
    }

    @Test
    public void test05() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getYear());
        // 从1开始
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());

        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
        // 2018-05-09T22:34:39.933
        System.out.println(now);
    }

    @Test
    public void test06() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("2018-05-09 22:27:34", formatter);
        System.out.println(dateTime);
    }

    @Test
    public void test07() {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        String format = now.format(formatter);
    }
}

class Person {
    public static enum Sex {
        MALE, FEMALE;
    }

    private String name;
    private Sex gender;
    private int age;
    private double height;

    public Person(String name, Sex gender, int age, double height) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
