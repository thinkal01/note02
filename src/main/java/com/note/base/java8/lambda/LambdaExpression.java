package com.note.base.java8.lambda;

import java.util.Comparator;
import java.util.function.*;

public class LambdaExpression {

    public static void main(String[] args) {
        Comparator<Apple> byColor = Comparator.comparing(Apple::getColor);

        // :: 运算符相当于 Function<T,R> T为参数,R为返回值
        Function<String, Integer> flambda = String::length;

        Predicate<Apple> predicate = a -> "green".equals(a.getColor());

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
}
