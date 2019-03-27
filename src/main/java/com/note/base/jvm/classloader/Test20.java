package com.note.base.jvm.classloader;

import java.lang.reflect.Method;

/*

类加载器的双亲委托模型的好处

1、可以确保Java核心库的类型安全：所有的Java应用都至少会引用java.lang.Object类，也就是说在运行器，java.lang.Object类会被加载到
Java虚拟机中；如果这个加载过程是由Java应用自己的类加载器所完成的，那么很可能就会在JVM中存在多个版本的java.lang.Object类，而这些
类之间是相互不兼容的，相互不可见的（正是命名空间发挥着作用）
借助于双亲委托机制，Java核心库中的类的加载工作都是由启动类加载器来统一完成，从而确保了Java应用所使用的都是同一个版本的Java核心类库，
它们之间是相互兼容的
2、可以确保Java核心类库所提供的类不会被自定义类所替代
3、不同的类加载器可以为相同名称(binary name)的类创建额外的命名空间，相同名称的类可以存在Java虚拟机中，只需要用不同的类加载器来
加载它们即可。不同类加载器所加载的类之间是不兼容的，这就相当于在Java虚拟机内部创建了一个又一个相互隔离的Java类空间，这类技术在
很多框架中都得到了实际应用。


 */
public class Test20 {
    public static void main(String[] args) throws Exception {
        Test16 loader1 = new Test16("loader1");
        Test16 loader2 = new Test16("loader2");

        loader1.setPath("/Users/zhouyilin/Desktop/test/");
        loader2.setPath("/Users/zhouyilin/Desktop/test/");

        Class<?> clazz1 = loader1.loadClass("com.eli.jvm.classloader.MyPerson");
        Class<?> clazz2 = loader2.loadClass("com.eli.jvm.classloader.MyPerson");

        /*
          clazz1和clazz2都是由系统类加载器加载，因此true
          如果把clazz1和clazz2都从classpath下删除，去加载桌面的class文件，那么loader1和loader2会处于不同的命名空间
          这时候会打印false，并且调用
               Method method = clazz1.getMethod("setMyPerson", Object.class);
               method.invoke(object1, object2);
          会抛异常。原因是不同命名空间的类加载器所加载的类相互不可见。
         */
        System.out.println(clazz1 == clazz2);

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        method.invoke(object1, object2);


    }
}
