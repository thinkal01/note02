package com.note.base;

import org.junit.Test;

public class Student01 implements Cloneable {
    private String name;
    private int age;

    @Test
    public void test01() {
        Student01 student01 = new Student01();
        int hashCode = student01.hashCode();
        // 2a742aa2
        String s = Integer.toHexString(hashCode);
        // com.note.test01.Test02@2a742aa2
        String s1 = student01.toString();
    }

    public Student01() {
        super();
    }

    public Student01(String name, int age) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Student01 other = (Student01) obj;

        if (age != other.age)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;

        return true;
    }

    /*
     *	protected void finalize()：当垃圾回收器确定不存在对该对象的更多引用时，由对象的垃圾回收器调用此方法。用于垃圾回收，但是什么时候回收不确定
     *	protected Object clone():创建并返回此对象的一个副本
     *
     *  Cloneable:此类实现了Cloneable接口,Object.clone()方法可以合法地对该类实例按字段复制
     *  这个接口是标记接口，告诉我们实现该接口的类就可以实现对象的复制了
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        Student01 p1 = new Student01("张三", 20);
        Student01 p2 = (Student01) p1.clone();
        p2.setName("李四");
        System.out.println("原始对象：" + p1);
        System.out.println("克隆之后的对象：" + p2);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("当前的对象被回收了" + this);
        super.finalize();
    }

    @Override
    public String toString() {
        return "Student01{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
