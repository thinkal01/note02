package com.note.base;

import java.io.Serializable;

/*
 * NotSerializableException:未序列化异常
 *
 * 类通过实现 java.io.Serializable 接口以启用其序列化功能。未实现此接口的类将无法使其任何状态序列化或反序列化。
 * 该接口没有任何方法，类似于这种没有方法的接口被称为标记接口。
 *
 * java.io.InvalidClassException:
 * cn.itcast_07.Person; local class incompatible:
 * stream classdesc serialVersionUID = -2071565876962058344,
 * local class serialVersionUID = -8345153069362641443
 *
 * 为什么会有问题呢?
 *  Person类实现了序列化接口，那么它本身也应该有一个标记值。
 *  这个标记值假设是100。
 *  开始的时候：
 *  Person.class -- id=100
 *  write数据：oos.txt -- id=100
 *  read数据: oos.txt -- id=100
 *
 *  现在：
 *  Person.class -- id=200
 *  write数据：oos.txt -- id=100
 *  read数据: oos.txt -- id=100
 *
 * 我们在实际开发中，可能还需要使用以前写过的数据，不能重新写入。怎么办呢?
 * 因为它们的id值不匹配。
 * 每次修改java文件的内容的时候,class文件的id值都会发生改变。
 * 而读取文件的时候，会和class文件中的id值进行匹配。
 * 让这个id值在java文件中是一个固定的值，这样修改文件的时候，这个id值不会发生改变
 * 点击鼠标即可。你难道没有看到黄色警告线吗?
 *
 *  看到类实现了序列化接口的时候，要想解决黄色警告线问题，就可以自动产生一个序列化id值。
 *  而且产生这个值以后，我们对类进行任何改动，它读取以前的数据是没有问题的。
 *
 * Serializable:用于给被序列化的类加入ID号。
 * 用于判断类和对象是否是同一个版本。
 */
public class Person implements Serializable/*标记接口*/ {
    private static final long serialVersionUID = -2071565876962058344L;

    private String name;

    /*
     * transient:非静态数据不想被序列化可以使用这个关键字修饰。
     */
    // private int age;
    private transient int age;

    public Person() {
        super();
    }

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
}
