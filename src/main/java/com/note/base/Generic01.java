package com.note.base;

public class Generic01 {
    public static void main(String args[]) {
        Integer i[] = fun1(1, 2, 3, 4, 5, 6);    // 返回泛型数组
        fun2(i);
    }

    public static <T> T[] fun1(T... arg) {    // 接收可变参数
        return arg;            // 返回泛型数组
    }

    public static <T> void fun2(T param[]) {
        System.out.print("接收泛型数组：");
        for (T t : param) {
            System.out.print(t + "、");
        }
    }
}

class Info3<T extends Number> {
    private T var;

    public T getVar() {
        return this.var;
    }

    public void setVar(T var) {
        this.var = var;
    }

    public String toString() {
        return this.var.toString();
    }
}

class Info3Test {
    public static void main(String args[]) {
        Info3<Integer> i = fun(30);
        System.out.println(i.getVar());
    }

    public static <T extends Number> Info3<T> fun(T param) {
        Info3<T> temp = new Info3<>();
        temp.setVar(param);
        return temp;
    }
}

class Info1<T, V> {        // 接收两个泛型类型
    private T var;
    private V value;

    public Info1(T var, V value) {
        this.setVar(var);
        this.setValue(value);
    }

    public void setVar(T var) {
        this.var = var;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public T getVar() {
        return this.var;
    }

    public V getValue() {
        return this.value;
    }
}

class Demo<S> {
    private S info;

    public Demo(S info) {
        this.setInfo(info);
    }

    public void setInfo(S info) {
        this.info = info;
    }

    public S getInfo() {
        return this.info;
    }
}

class GenericsDemo {
    public static void main(String args[]) {
        Demo<Info1<String, Integer>> d;        // 将Info作为Demo的泛型类型
        Info1<String, Integer> i;    // Info指定两个泛型类型
        i = new Info1<>("李兴华", 30);     // 实例化Info对象
        d = new Demo<>(i);    // 在Demo类中设置Info类的对象
        System.out.println("内容一：" + d.getInfo().getVar());
        System.out.println("内容二：" + d.getInfo().getValue());
    }
}