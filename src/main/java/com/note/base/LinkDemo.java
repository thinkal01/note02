package com.note.base;

import org.junit.Test;

class Node {        // 定义节点类
    private String data;    // 保存节点内容
    private Node next;      // 表示保存下一个节点

    public Node(String data) {
        this.data = data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return this.next;
    }

    public String getData() {
        return this.data;
    }
}

public class LinkDemo {
    public static void main(String args[]) {
        Node root = new Node("火车头");  // 定义根节点
        Node n1 = new Node("车厢-A");    // 定义第一个车厢（第一个节点）
        Node n2 = new Node("车厢-B");    // 定义第二个车厢（第二个节点）
        Node n3 = new Node("车厢-C");    // 定义第三个车厢（第三个节点）
        root.setNext(n1);            // 设置火车头的下一个节点是第一个车厢A
        n1.setNext(n2);            // 设置第一个车厢的下一个节点是第二个车厢
        n2.setNext(n3);            // 设置第二个车厢的下一个节点是第三个车厢
        printNode(root);            // 从头开始输出
    }

    public static void printNode(Node node) {    // 输出节点
        System.out.print(node.getData() + "\t");    // 输出节点的内容
        if (node.getNext() != null) {        // 判断此节点是否存在下一个节点
            printNode(node.getNext());    // 向下继续输出
        }
    }

    @Test
    public void testLink() {
        Link l = new Link();
        l.addNode("A");        // 增加节点
        l.addNode("B");        // 增加节点
        l.addNode("C");        // 增加节点
        l.addNode("D");        // 增加节点
        l.addNode("E");        // 增加节点
        System.out.println("======= 删除之前 ========");
        l.printNode();
        System.out.println(l.contains("X"));
        l.deleteNode("C");        // 删除节点
        l.deleteNode("D");        // 删除节点
        l.deleteNode("A");        // 删除节点
        System.out.println("\n====== 删除之后 =========");
        l.printNode();
        System.out.println("\n查询节点：" + l.contains("B"));
    }
}

class Link {        // 链表的完成类
    class Node {    // 保存每一个节点，此处为了方便直接定义成内部类
        private String data;    // 保存节点的内容
        private Node next;      // 保存下一个节点

        public Node(String data) {
            this.data = data;        // 通过构造方法设置节点内容
        }

        public void add(Node newNode) {        // 将节点加入到合适的位置
            if (this.next == null) {           // 如果下一个节点为空，则把新节点设置在next的位置上
                this.next = newNode;
            } else {        // 如果不为空，则需要向下继续找next
                this.next.add(newNode);
            }
        }

        public void print() {
            System.out.print(this.data + "\t");    // 输出节点内容
            if (this.next != null) {        // 还有下一个元素，需要继续输出
                this.next.print();    // 下一个节点继续调用print
            }
        }

        public boolean search(String data) {    // 内部搜索的方法
            if (data.equals(this.data)) {        // 判断输入的数据是否和当前节点的数据一致
                return true;
            } else {    // 向下继续判断
                if (this.next != null) {    // 下一个节点如果存在，则继续查找
                    return this.next.search(data);    // 返回下一个的查询结果
                } else {
                    return false;        // 如果所有的节点都查询完之后，没有内容相等，则返回false
                }
            }
        }

        public void delete(Node previous, String data) {
            if (data.equals(this.data)) {    // 找到了匹配的节点
                previous.next = this.next;    // 空出当前的节点
            } else {
                if (this.next != null) {    // 还是存在下一个节点
                    this.next.delete(this, data);    // 继续查找
                }
            }
        }
    }

    private Node root;        // 链表中必然存在一个根节点

    public void addNode(String data) {    // 增加节点
        Node newNode = new Node(data);    // 定义新的节点
        if (this.root == null) {            // 没有根节点
            this.root = newNode;    // 将第一个节点设置成根节点
        } else {        // 不是根节点，放到最后一个节点之后
            this.root.add(newNode);    // 通过Node自动安排此节点放的位置
        }
    }

    public void printNode() {        // 输出全部的链表内容
        if (this.root != null) {     // 如果根元素不为空
            this.root.print();    // 调用Node类中的输出操作
        }
    }

    public boolean contains(String name) {    // 判断元素是否存在
        return this.root.search(name);    // 调用Node类中的查找方法
    }

    public void deleteNode(String data) {        // 删除节点
        if (this.contains(data)) {    // 判断节点是否存在
            // 一定要判断此元素现在是不是根元素相等的
            if (this.root.data.equals(data)) {    // 内容是根节点
                this.root = this.root.next;    // 修改根节点，将第一个节点设置成根节点
            } else {
                this.root.next.delete(root, data);    // 把下一个节点的前节点和数据一起传入进去
            }
        }
    }
}