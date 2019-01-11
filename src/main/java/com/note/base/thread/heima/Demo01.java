package com.note.base.thread.heima;

import java.util.Arrays;
import java.util.List;

public class Demo01 {

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80);
        int res = add(values);
        System.out.println("计算的结果为：" + res);

        // 当前活动线程的数量
        System.out.println(Thread.activeCount());
    }

    public static int add(List<Integer> values) {
        // 并行打印
        // values.parallelStream().forEach(System.out :: println);
        return values.parallelStream().mapToInt(i -> i * 2).sum();
    }

}
