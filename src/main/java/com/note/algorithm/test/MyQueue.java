package com.note.algorithm.test;

public class MyQueue {
    // 数组
    private long[] arr;

    // 最大空间
    private int maxSize;

    // 有效元素大小
    private int elems;

    // 队头
    private int font;

    // 队尾
    private int end;

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new long[maxSize];
        elems = 0;
        font = 0;
        end = -1;
    }

    public static void main(String[] args) {
        MyQueue mq = new MyQueue(5);
        System.out.println(mq.isEmpty());
        mq.insert(30);
        mq.insert(20);
        mq.insert(10);
        mq.insert(2);
        mq.insert(1);
        mq.insert(20);

        while (!mq.isEmpty()) {
            long tmp = mq.remove();
            System.out.println(tmp + " ");
        }
    }

    // 插入数据
    public void insert(long value) {
        if (end == maxSize - 1) {
            end = -1;
        }
        arr[++end] = value;
        elems++;
    }

    // 移除数据
    public long remove() {
        long tmp = arr[font++];

        if (font == maxSize) {
            font = 0;
        }
        elems--;
        return tmp;
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
}