package com.note.designpattern.iterator;

public class GenericArrayList<E> {
    E[] datas = (E[]) new Object[10];
    int index = 0;

    public void add(E o) {
        if (index == datas.length) {
            E[] newObjects = (E[]) new Object[datas.length * 2];
            System.arraycopy(datas, 0, newObjects, 0, datas.length);
            datas = newObjects;
        }
        datas[index] = o;
        index++;
    }

    public int size() {
        return index;
    }

    public static void main(String args[]) {
        GenericArrayList<String> a = new GenericArrayList<>();
        a.add("hello");
    }
}
