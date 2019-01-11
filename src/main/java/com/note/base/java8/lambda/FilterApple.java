package com.note.base.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.LongPredicate;

public class FilterApple {

    // 可省略
//    @FunctionalInterface
    public interface AppleFilter {
        boolean filter(Apple apple);
    }

    public static List<Apple> findApple(List<Apple> appleList, AppleFilter appleFilter) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : appleList) {
            if (appleFilter.filter(apple)) {
                list.add(apple);
            }
        }

        return list;
    }

    public static class GreenAnd150WeightFilter implements AppleFilter {
        @Override
        public boolean filter(Apple apple) {
            return "green".equals(apple.getColor()) && apple.getWeight() >= 150;
        }
    }

    public static List<Apple> filterByWeight(List<Apple> source, LongPredicate predicate) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : source) {
            if (predicate.test(apple.getWeight())) {
                list.add(apple);
            }
        }

        return list;
    }

    public static List<Apple> filterByWeight(List<Apple> source, BiPredicate<String, Long> predicate) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : source) {
            if (predicate.test(apple.getColor(), apple.getWeight())) {
                list.add(apple);
            }
        }

        return list;
    }

    public static void simpleTestConsumer(List<Apple> source, Consumer<Apple> consumer) {
        for (Apple apple : source) {
            consumer.accept(apple);
        }
    }

    public static Apple testBiFunction(String color, long weight, BiFunction<String, Long, Apple> biFunction) {
        return biFunction.apply(color, weight);
    }

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 150), new Apple("yellow", 100));
        List<Apple> appleList = findApple(list, new GreenAnd150WeightFilter());
        List<Apple> appleList1 = findApple(list, apple -> "green".equals(apple.getColor()) && apple.getWeight() >= 150);

        filterByWeight(list, w -> w > 100);
        filterByWeight(list, (c, w) -> "green".equals(c) && w > 100);
        simpleTestConsumer(list, apple -> System.out.println(apple));
        testBiFunction("green", 100, (c, w) -> new Apple(c, w));

        list.stream().forEach(apple -> System.out.println(apple));
        list.stream().forEach(System.out::println);
    }
}
