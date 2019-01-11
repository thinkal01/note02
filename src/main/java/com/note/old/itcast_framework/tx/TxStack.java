package com.note.old.itcast_framework.tx;

import java.util.LinkedList;

public class TxStack {
    private static LinkedList<Object> stack = new LinkedList<>();

    public static void push(Object service) {
        stack.push(service);
    }

    public static Object pop() {
        return stack.pop();
    }

    public static boolean isEmpty() {
        return stack.isEmpty();
    }
}
