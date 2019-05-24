package com.note.algorithm.test;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class MyPriorityQueue {
    // 数组
    private long[] arr;

    // 最大空间
    private int maxSize;

    // 有效元素大小
    private int elems;

    public MyPriorityQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new long[maxSize];
        elems = 0;
    }

    public static void main(String[] args) {
        MyPriorityQueue pq = new MyPriorityQueue(10);
        pq.insert(30);
        pq.insert(45);
        pq.insert(15);
        pq.insert(2);
        pq.insert(1);

        while (!pq.isEmpty()) {
            long value = pq.remove();
            System.out.print(value + " ");
        }
    }

    // 插入数据
    public void insert(long value) {
        int i;
        for (i = 0; i < elems; i++) {
            if (value < arr[i]) {
                break;
            }
        }

        for (int j = elems; j > i; j--) {
            arr[j] = arr[j - 1];
        }
        arr[i] = value;
        elems++;
    }

    // 移除数据
    public long remove() {
        long value = arr[elems - 1];
        elems--;
        return value;
    }

    // 是否为空
    public boolean isEmpty() {
        return (elems == 0);
    }

    // 是否满了
    public boolean isFull() {
        return (elems == maxSize);
    }

    // 返回有效元素大小
    public int size() {
        return elems;
    }

    @Test
    public void test() {
        PriorityQueue priorityQueue = new PriorityQueue();
        PriorityBlockingQueue queue = new PriorityBlockingQueue(100);
    }
}
