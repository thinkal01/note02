package com.note.base;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

public class Reflect01 {

    public void setProperty(Object obj, String propertyName, Object value) throws NoSuchFieldException, IllegalAccessException {
        // 根据对象获取字节码文件对象
        Class c = obj.getClass();
        // 获取该对象的propertyName成员变量
        Field field = c.getDeclaredField(propertyName);
        // 取消访问检查
        field.setAccessible(true);
        // 给对象的成员变量赋值为指定的值
        field.set(obj, value);
    }

    public void test() throws Exception {
        setProperty(new Person(), "name", "zhangsan");
    }

    public void class01() throws Exception {
        // 加载键值对数据
        Properties prop = new Properties();
        /*className=cn.itcast.test.Worker
        methodName=love*/
        FileReader fr = new FileReader("class.txt");
        prop.load(fr);
        fr.close();

        // 获取数据
        String className = prop.getProperty("className");
        String methodName = prop.getProperty("methodName");

        // 反射
        Class c = Class.forName(className);

        Constructor con = c.getConstructor();
        Object obj = con.newInstance();

        // 调用方法
        Method m = c.getMethod(methodName);
        m.invoke(obj);
    }

    public void class02() throws ClassNotFoundException {
        // 方式1
        Person p = new Person();
        Class c = p.getClass();

        Person p2 = new Person();
        Class c2 = p2.getClass();

        System.out.println(c == c2);// true

        // 方式2
        Class c3 = Person.class;
        // int.class;

        // 方式3
        Class c4 = Class.forName("cn.itcast_01.Person");
    }

    public void constructor01() throws Exception {
        // 获取字节码文件对象
        Class c = Class.forName("cn.itcast_01.Person");

        // 获取构造方法
        // public Constructor[] getConstructors():所有公共构造方法
        // public Constructor[] getDeclaredConstructors():所有构造方法
        Constructor[] cons = c.getDeclaredConstructors();
        for (Constructor con : cons) {
            System.out.println(con);
        }

        // 获取单个构造方法
        // public Constructor<T> getConstructor(Class<?>... parameterTypes)
        // 参数表示的是：你要获取的构造方法的构造参数个数及数据类型的class字节码文件对象
        Constructor con = c.getConstructor();// 返回的是构造方法对象
        con = c.getConstructor(String.class, int.class, String.class);
        // 获取私有构造方法
        con = c.getDeclaredConstructor(String.class);


        // public T newInstance(Object... initargs)
        // 使用Constructor 对象表示的构造方法来创建该构造方法声明类的新实例，并用指定的初始化参数初始化该实例。
        Object obj = con.newInstance();
        obj = con.newInstance("林青霞", 27, "北京");
        // 暴力访问
        con.setAccessible(true);// 值为true则指示反射的对象在使用时应该取消Java语言访问检查。
        obj = con.newInstance("风清扬");
    }

    public void field01() throws Exception {
        // 获取字节码文件对象
        Class c = Class.forName("cn.itcast_01.Person");

        // 获取所有的成员变量
        // Field[] fields = c.getFields();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        // 通过无参构造方法创建对象
        Constructor con = c.getConstructor();
        Object obj = con.newInstance();

        // 获取address并对其赋值
        Field addressField = c.getField("address");
        // public void set(Object obj,Object value)
        // 将指定Field 对象表示的字段设置为指定的新值。
        addressField.set(obj, "北京"); // 给obj对象的addressField字段设置值为"北京"

        // 获取name并对其赋值
        Field nameField = c.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(obj, "林青霞");
    }

    public void method() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 创建集合对象
        ArrayList<Integer> array = new ArrayList<>();

        Class c = array.getClass(); // 集合ArrayList的class文件对象
        Method m = c.getMethod("add", Object.class);

        m.invoke(array, "hello"); // 调用array的add方法，传入的值是hello
        m.invoke(array, "world");
        m.invoke(array, "java");
    }

    public void method01() throws Exception {
        // 获取字节码文件对象
        Class c = Class.forName("cn.itcast_01.Person");

        // 获取所有的方法
        // Method[] methods = c.getMethods(); // 获取自己的包括父亲的公共方法
        Method[] methods = c.getDeclaredMethods(); // 获取自己的所有的方法
        for (Method method : methods) {
            System.out.println(method);
        }

        Constructor con = c.getConstructor();
        Object obj = con.newInstance();

        // 获取单个方法并使用
        // public Method getMethod(String name,Class<?>... parameterTypes)
        // 第一个参数表示的方法名，第二个参数表示的是方法的参数的class类型
        Method m1 = c.getMethod("show");

        // public Object invoke(Object obj,Object... args)
        // 返回值是Object接收,第一个参数表示对象是谁，第二参数表示调用该方法的实际参数
        m1.invoke(obj); // 调用obj对象的m1方法

        Method m2 = c.getMethod("method", String.class);
        m2.invoke(obj, "hello");

        Method m4 = c.getDeclaredMethod("function");
        m4.setAccessible(true);
        m4.invoke(obj);
    }
}