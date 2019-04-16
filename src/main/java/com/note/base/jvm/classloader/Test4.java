package com.note.base.jvm.classloader;

public class Test4 {

    /*
     * 助记符：
     * anewarray：表示创建一个引用类型的（如类、接口、数组）数组，并将其引用值压入栈顶
     * newarray：表示创建一个指定的原始类型（如int、float、char等）数组，并将其引用值压入栈顶
     */
    public static void main(String[] args) {
        // MyParent4 myParent4 = new MyParent4();
        /*
        对于数组实例来说，其类型是由JVM在运行期动态生成的，[Lcom.jvm.classloader.MyParent4
        这种形式。动态生成的类型，其父类型就是Object。
        对于数组来说，JavaDoc经常将构成数组的元素为Component，实际上就是将数组降低一个维度后的类型。
         */
        MyParent4[] myParent4s = new MyParent4[1];
        System.out.println(myParent4s.getClass());

        MyParent4[][] myParent4s1 = new MyParent4[1][1];
        System.out.println(myParent4s1.getClass());

        // 数组类型调用getClassLoader的返回值与数组里的元素的返回值相同
        System.out.println(myParent4s.getClass().getClassLoader());
        System.out.println(myParent4s.getClass().getSuperclass());
        System.out.println(myParent4s1.getClass().getSuperclass());
        int[] ints = new int[1];
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getSuperclass());
    }
}

class MyParent4 {
    static {
        System.out.println("MyParent4 static block");
    }
}
