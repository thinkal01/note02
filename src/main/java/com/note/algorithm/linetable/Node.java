package com.note.algorithm.linetable;

/**
 * 单链表的结点
 */
public class Node {
    Object data;//要存储的数据
    Node next;

    public Node() {
    }

    public Node(Object data) {
        super();
        this.data = data;
    }

    public Node(Object data, Node next) {
        super();
        this.data = data;
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
