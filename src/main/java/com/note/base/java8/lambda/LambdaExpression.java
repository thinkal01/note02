package com.note.base.java8.lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.*;

public class LambdaExpression {

    public static void main(String[] args) {
        Comparator<Apple> byColor = Comparator.comparing(Apple::getColor);

        // :: 运算符相当于 Function<T,R> T为参数,R为返回值
        Function<String, Integer> flambda = String::length;

        // 返回boolean
        Predicate<Apple> predicate = a -> "green".equals(a.getColor());

        // 无参返回结果
        Supplier<Apple> supplier = Apple::new;

        IntFunction<Double> intFunction = i -> i * 100.0;
        Double apply = intFunction.apply(20);

        Function<String, Integer> function = Integer::parseInt;
        Integer apply1 = function.apply("123");

        BiFunction<String, Integer, Character> biFunction = String::charAt;
        Character c = biFunction.apply("hello", 2);

        String s = new String("hello");
        Function<Integer, Character> function1 = s::charAt;

        Supplier<String> supplier1 = String::new;
        String s1 = supplier1.get();

        BiFunction<String, Long, Apple> biFunction1 = Apple::new;
        Apple red = biFunction1.apply("red", (long) 100);

        // method reference
        Consumer<String> consumer = System.out::println;
        useConsumer(consumer, "Hello");
        useConsumer(System.out::println, "Hello");
    }

    public static <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
    }

    // 模拟comparing
    public <T, R extends Comparable<R>> Comparator<T> comparing(Function<T, R> function) {
        return (Comparator<T>) (T o1, T o2) -> function.apply(o1).compareTo(function.apply(o2));
    }

    @Test
    public void test2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        list.forEach(i -> System.out.println(i));
        list.forEach(System.out::println);

        List<String> list1 = Arrays.asList("hello", "world");
        list1.stream().map(String::toUpperCase).forEach(System.out::print);

        Function<String, String> function = String::toUpperCase;
        String s = function.apply("a");

        Collections.sort(Arrays.asList("b", "a", "c"), String::compareTo);

        Function<Integer, Integer> function1 = value -> value * 2;
        Function<Integer, Integer> function2 = value -> value * value;
        // 函数串联,先执行function2,再执行function1
        System.out.println(function1.compose(function2).apply(2));
        // 先执行function1
        System.out.println(function1.andThen(function2).apply(2));

        Predicate<Integer> predicate = item -> item > 5;
        Predicate<Integer> predicate2 = item -> item % 2 == 0;
        Predicate<Integer> and = predicate.and(predicate2);
        Predicate<Integer> or = predicate.or(predicate2);

        // 判断两个参数是否相等
        Predicate<Object> equal = Predicate.isEqual("abc");
        boolean b = equal.test("adc");
        // BiFunction 子类
        BinaryOperator<Integer> binaryOperator = (m, n) -> m + n;
        BinaryOperator.minBy(String::compareTo);
    }

    @Test
    public void test3() {
        // 非null
        Optional<String> optional = Optional.of("hello");
        // null
        optional = Optional.empty();
        // null或非null使用
        optional = Optional.ofNullable("");
        // if (optional.isPresent()) {
        //     System.out.println(optional.get());
        // }
        // 使用方式
        optional.ifPresent(System.out::println);
        // 没有值,使用默认值
        System.out.println(optional.orElse("world"));
        System.out.println(optional.orElseGet(() -> "world"));
        // 返回集合
        List<String> list = optional.map(s -> Arrays.asList(s)).orElse(Collections.emptyList());
    }

    @Test
    public void test4() {
        List<Integer> list = Arrays.asList(6, 4, 3, 2, 1);
        // 静态方法
        list.sort(Integer::compare);
        // 实例对象::实例方法 new MyCompare()::myCompare
        // 类::实例方法,第一个参数调用方法,其他参数作为方法参数
        list.sort(Integer::compareTo);
        // 类名::new
        Function<String, String> function = String::new;
        String s = function.apply("abc");
    }
}