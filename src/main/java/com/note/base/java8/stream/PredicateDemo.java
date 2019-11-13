package com.note.base.java8.stream;

import java.util.function.Predicate;

public class PredicateDemo {

    public void test() {
        /*
         * 1.predicate test()方法
         * */
        Predicate<Integer> predicateInt = i -> i > 0;
        //使用test()方法执行断言,输出true,false
        System.out.println(predicateInt.test(5));
        /*
         * 2.predicate add(),or(),negate()方法
         * */
        Predicate<String> predicate = s -> s.equals("String");
        //and()方法
        Predicate<String> predicateAnd = predicate.and(s -> s.length() > 5);
        //or()方法
        Predicate<String> predicateOr = predicate.or(s -> s.length() == 4);
        //negate()方法,取反
        Predicate<String> predicateNegate = predicate.negate();
        System.out.println(predicateNegate.test("String"));

        // 判断两个参数是否相等
        Predicate<Object> equal = Predicate.isEqual("abc");
        boolean b = equal.test("adc");
    }
}