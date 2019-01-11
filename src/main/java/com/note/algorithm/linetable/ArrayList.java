package com.note.algorithm.linetable;

import java.util.Arrays;

/**
 * 顺序表
 * 底层采用的数组，但是长度可以动态变化
 * java.util.ArrayList 每次增长50%
 */
public class ArrayList implements List {
    private Object[] elementData; //底层是一个数组，目前还没有确定长度
    private int size;  //不是数组分配了几个空间，而是元素的个数

    public ArrayList() {
        //没有指定长度，默认长度是4
        this(4);
    }

    /**
     * @param initialCapacity 指定数组的初始长度
     */
    public ArrayList(int initialCapacity) {
        //给数组分配指定数量的空间
        elementData = new Object[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int i) {
        if (i < 0 || i >= size) {
            throw new MyArrayIndexOutOfBoundsException("数组索引越界异常：" + i);
        }
        return elementData[i];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object e) {
        return false;
    }

    @Override
    public int indexOf(Object e) {
        return 0;
    }

    @Override
    public void add(int i, Object e) {
        //i的位置要正确
        if (i < 0 || i > size) {
            throw new MyArrayIndexOutOfBoundsException("数组索引越界异常:" + i);
        }
        //数组满了，就扩容
        if (size == elementData.length) {
            grow();
        }
        //后移i及其后面元素，从最后一个元素开始
        for (int j = size; j > i; j--) {
            elementData[j] = elementData[j - 1];
        }

        //给数组第i个位置赋值
        elementData[i] = e;
        //元素个数+1
        size++;
    }

    @Override
    public void add(Object e) {
        this.add(size, e);
    }

    private void grow() {
        elementData = Arrays.copyOf(elementData, elementData.length * 2);
    }

    @Override
    public boolean addBefore(Object obj, Object e) {
        return false;
    }

    @Override
    public boolean addAfter(Object obj, Object e) {
        return false;
    }

    @Override
    public Object remove(int i) {
        return null;
    }

    @Override
    public boolean remove(Object e) {
        return false;
    }

    @Override
    public Object replace(int i, Object e) {
        return null;
    }

    //[123,321,456,666,678.....]
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                builder.append(elementData[i] + ",");
            } else {
                builder.append(elementData[i]);
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
