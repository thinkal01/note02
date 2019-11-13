package com.note.base.java8.stream;

import lombok.Getter;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamTest1 {

    // 创建Stream
    @Test
    public void test() {
        Stream<String> stream = Stream.of("hello", "world", "hello world");

        // 通过数组创建流
        String[] myArray = new String[]{"hello", "world", "hello world"};
        Stream stream2 = Stream.of(myArray);
        Stream stream3 = Arrays.stream(myArray);

        List<String> list = Arrays.asList(myArray);
        Stream stream4 = list.stream();

        IntStream intStream = IntStream.of(new int[]{5, 6, 7});
        // 不包括8
        IntStream intStream1 = IntStream.range(3, 8);
        // 包括8
        IntStream intStream2 = IntStream.rangeClosed(3, 8);

        // 流转数组
        String[] strings = stream.toArray(length -> new String[length]);
        String[] stringArray = stream.toArray(String[]::new);
        Arrays.asList(stringArray).forEach(System.out::println);
    }

    @Test
    public void test2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        // 初始值0,累加,21
        Integer reduce = list.stream().reduce(0, Integer::sum);
    }

    // collect 操作
    @Test
    public void test3() {
        Stream<String> stream = Stream.of("hello", "world", "helloworld");

        // 第一个参数返回值,第二个参数遍历元素添加到list,第三个参数添加得到的所有list到第三个list2中返回
        // List<String> list = stream.collect(() -> new ArrayList(), (theList, item) -> theList.add(item),
        //         (theList1, theList2) -> theList1.addAll(theList2));
        List<String> list = stream.collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        // List<String> list = stream.collect(Collectors.toList());

        List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
        Set<String> set = stream.collect(Collectors.toCollection(TreeSet::new));
        // 拼接成一个字符串
        String str = stream.collect(Collectors.joining());
        System.out.println(str);
    }

    // map操作
    @Test
    public void test4() {
        List<String> list = Arrays.asList("hello", "world", "helloworld", "test");
        list.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);

        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        // 合并为一个流
        stream.flatMap(theList -> theList.stream()).map(item -> item * item).forEach(System.out::println);
    }

    @Test
    public void test5() {
        // Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);
        // stream = Stream.empty();
        // stream.findFirst().ifPresent(System.out::println);

        // 1 3 5 7 ...,限制迭代次数为6
        Stream<Integer> stream = Stream.iterate(1, item -> item + 2).limit(6);
        // 找出大于2的,然后每个元素乘2,忽略掉前两个元素,再取出前两个元素求和
        System.out.println(stream.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).sum());
        // 求最大值
        stream.filter(item -> item > 200).mapToInt(item -> item * 2).limit(2).max().ifPresent(System.out::println);

        IntSummaryStatistics summaryStatistics = stream.mapToInt(item -> item * 2).limit(2).summaryStatistics();
        System.out.println(summaryStatistics.getMin());
        System.out.println(summaryStatistics.getMax());
        // 数量
        System.out.println(summaryStatistics.getCount());

        System.out.println(stream);
        System.out.println(stream.filter(item -> item > 2));
        // 去重,抛异常,流未操作结束
        System.out.println(stream.distinct());

        // 返回新stream
        Stream<Integer> stream2 = stream.filter(item -> item > 2);
        System.out.println(stream2);
        // 返回新stream
        Stream<Integer> stream3 = stream2.distinct();
        System.out.println(stream3);
    }


    public void test6() {
        List<String> list = Arrays.asList("hello", "world", "hello world");
        list.stream().map(item -> {
            String result = item.substring(0, 1).toUpperCase() + item.substring(1);
            // map中间操作遇到终止操作才会执行
            System.out.println("test");
            return result;
        });

        // 无限迭代
        IntStream.iterate(0, i -> (i + 1) % 2).distinct().limit(6).forEach(System.out::println);
        IntStream.iterate(0, i -> (i + 1) % 2).limit(6).distinct().forEach(System.out::println);
    }

    public void test7() {
        List<String> list = new ArrayList<>(5000000);
        for (int i = 0; i < 5000000; ++i) {
            list.add(UUID.randomUUID().toString());
        }

        long startTime = System.nanoTime();
        // 并行流,比串行流快
        list.parallelStream().sorted();
        long endTime = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("排序耗时：" + millis);
    }

    public void test8() {
        List<String> list = Arrays.asList("hello", "world", "hello world");

        // 结果 hello 5
        // 对一个元素,应用完第一个操作,接着应用第二个,操作串行
        // 短路操作,找到符合的则不再执行
        list.stream().mapToInt(item -> {
            int length = item.length();
            System.out.println(item);
            return length;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);

        list = Arrays.asList("hello welcome", "world hello", "hello world hello", "hello welcome");
        // 拆分去重
        List<String> result = list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        result = list.stream().map(item -> item.split(" ")).flatMap(Stream::of).collect(Collectors.toList());
    }

    public void test9() {
        List<String> list1 = Arrays.asList("Hi", "Hello", "你好");
        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");
        // Hi zhangsan Hi lisi ...
        List<String> result = list1.stream().flatMap(item -> list2.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList());
    }

    public void test10() {
        Student student1 = new Student("zhangsan", 100);
        Student student2 = new Student("lisi", 90);
        Student student3 = new Student("wangwu", 90);
        Student student4 = new Student("zhangsan", 80);

        List<Student> students = Arrays.asList(student1, student2, student3, student4);
        // 分组
        // Map<String, List<Student>> map = students.stream().collect(Collectors.groupingBy(Student::getName));
        // 姓名,个数
        // Map<String, Long> map = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
        // 姓名,分数平均值
        // Map<String, Double> map = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.averagingDouble(Student::getScore)));
        // 分区,只有两组
        Map<Boolean, List<Student>> map = students.stream().
                collect(Collectors.partitioningBy(student -> student.getScore() >= 90));
        System.out.println(map);
    }

    public void test11() {
        Student student1 = new Student("zhangsan", 80);
        Student student2 = new Student("lisi", 90);
        Student student3 = new Student("wangwu", 100);
        Student student4 = new Student("zhaoliu", 80);
        Student student5 = new Student("zhaoliu", 90);

        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5);
        // List<Student> students1 = students.stream().collect(toList());
        // 统计总数
        // System.out.println("count: " + students.stream().collect(counting()));
        // System.out.println("count: " + students.stream().count());

        // 分数最小学生
        // students.stream().collect(minBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        // students.stream().collect(minBy(Comparator.comparing(Student::getScore))).ifPresent(System.out::println);;
        // 分数最高学生
        // students.stream().collect(maxBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        // 平均分
        // System.out.println(students.stream().collect(averagingInt(Student::getScore)));
        // 总分
        // System.out.println(students.stream().collect(summingInt(Student::getScore)));
        // 最小,最大,平均,总分,总数
        // IntSummaryStatistics intSummaryStatistics = students.stream().collect(summarizingInt(Student::getScore));
        // System.out.println(intSummaryStatistics);

        // 拼接
        // System.out.println(students.stream().map(Student::getName).collect(joining()));
        // 添加分隔符
        // System.out.println(students.stream().map(Student::getName).collect(joining(", ")));
        // 添加前后缀
        // System.out.println(students.stream().map(Student::getName).collect(joining(", ", "<begin> ", " <end>")));
        // 根据分数分组后再根据名字分组
        // Map<Integer, Map<String, List<Student>>> map = students.stream().collect(groupingBy(Student::getScore, groupingBy(Student::getName)));

        // 分区
        // Map<Boolean, List<Student>> map2 = students.stream().collect(partitioningBy(student -> student.getScore() > 80));
        // 二级分区
        // Map<Boolean, Map<Boolean, List<Student>>> map3 = students.stream().
        //         collect(partitioningBy(student -> student.getScore() > 80, partitioningBy(student -> student.getScore() > 90)));
        // 分区数量
        // Map<Boolean, Long> map4 = students.stream().collect(partitioningBy(student -> student.getScore() > 80, counting()));
        // 姓名分组,取分数最小的
        Map<String, Student> map5 = students.stream().collect(
                groupingBy(Student::getName, collectingAndThen(minBy(Comparator.comparingInt(Student::getScore)), Optional::get)));
        System.out.println(map5);
    }

    // match操作
    @Test
    public void test12() {
        List<String> strs = Arrays.asList("a", "a", "a", "a", "b");
        // 任意一个元素成功，返回true
        System.out.println(strs.stream().anyMatch("a"::equals));
        // 所有的都是，返回true
        strs.stream().allMatch(str -> str.equals("a"));
        // 所有的都不是，返回true
        strs.stream().noneMatch(str -> str.equals("a"));
    }

    @Getter
    class Student {
        private String name;
        private int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}
