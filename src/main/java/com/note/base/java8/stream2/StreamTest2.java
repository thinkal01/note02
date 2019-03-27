package com.note.base.java8.stream2;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest2 {

    public void test1() {
        List<String> list = Arrays.asList("hello", "world", "hello world");
        NullPointerException nullPointerException = new NullPointerException("my exception");

        try (Stream<String> stream = list.stream()) {
            stream.onClose(() -> {
                System.out.println("aaa");
                // throw new NullPointerException("first exception");
                // throw nullPointerException;
                throw new NullPointerException("first exception");
            }).onClose(() -> {
                System.out.println("bbb");
                // throw new ArithmeticException("second exception");
                // throw nullPointerException;
                throw new NullPointerException("second exception");
            }).forEach(System.out::println);
        }
    }

    @Test
    public void test2() {
        List<String> list = Arrays.asList("hello", "world", "welcome");
        Stream<String> stream = list.stream();

        Stream<String> stream2 = stream.map(item -> item + "_abc");
        stream2.forEach(System.out::println);

        // 不变
        list.stream().forEach(System.out::println);
        list.forEach(System.out::println);
    }
}
