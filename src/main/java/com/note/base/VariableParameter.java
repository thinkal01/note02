package com.note.base;

/**
 * 可变参数
 * 1.在方法中定义可变参数后，我们可以像操作数组一样操作该参数；
 * 2.如果该方法除了可变参数还有其它的参数，可变参数必须放到最后；
 * 3.调用使用了可变参数的方法时：
 * a.可以不写参数，即传入空参；
 * b.可以直接在里边写入参数，参数间用逗号隔开；
 * c.可以传入一个数组；
 * 4.拥有可变参数的方法可以被重载，在被调用时，如果能匹配到参数定长的方法则优先调用参数定长的方法。
 */
public class VariableParameter {
    public void comp(int... items) {
        System.out.println("1");
    }

    public void comp(int item1, int item2) {
        System.out.println("2");
    }

    public static void main(String[] args) {
        VariableParameter vp = new VariableParameter();
        vp.comp(1, 2);
    }

    public static void changParams(String... strs) {
        System.out.println(strs); // [Ljava.lang.String;@4f19c297
        System.out.println(strs.length);

        changParams2(strs);
    }

    public static void changParams2(String... strs) {
        System.out.println(strs); // [Ljava.lang.String;@4f19c297
        System.out.println(strs[0]);
        System.out.println(strs.length);
    }
}
