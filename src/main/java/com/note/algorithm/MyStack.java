package com.note.algorithm;

import java.util.LinkedList;

/**
 * 自定义的栈集合
 */
public class MyStack<T> {
    private LinkedList<T> link;

    public MyStack() {
        link = new LinkedList<>();
    }

    public void add(T t) {
        link.addFirst(t);
    }

    public T get() {
        return link.removeFirst();
    }

    public boolean isEmpty() {
        return link.isEmpty();
    }
}

