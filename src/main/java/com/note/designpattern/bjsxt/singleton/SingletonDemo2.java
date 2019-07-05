package com.note.designpattern.bjsxt.singleton;

import java.io.*;
import java.lang.reflect.Constructor;

public class SingletonDemo2 {
    /**
     * volatile关键字的作用是禁止指令重排
     * 在语句1中并不是一个原子操作，在JVM中其实是3个操作：
     * 1.给instance分配空间
     * 2.调用构造函数来初始化
     * 3.将instance对象指向分配的内存空间（instance指向分配的内存空间后就不为null了）
     * 在JVM中的及时编译存在指令重排序的优化，不能保证1,2,3执行顺序，最终执行顺序可能是 1-3-2。
     * 如果是 1-3-2，则在 3 执行完毕、2 未执行之前，被线程二抢占了，这时 instance 已经是非 null（但却没有初始化）
     * 线程二会直接返回 instance使用，然后顺理成章地报错。
     */
    private static volatile SingletonDemo2 instance;

    private SingletonDemo2() {
    }

    public static SingletonDemo2 getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo2.class) {
                if (instance == null) {
                    instance = new SingletonDemo2();
                }
            }
        }
        return instance;
    }
}

/**
 * 双重检查锁实现单例模式
 */
class SingletonDemo3 {
    private static SingletonDemo3 instance = null;

    public static SingletonDemo3 getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo3.class) {
                if (instance == null) {
                    // 防止指令重排序2
                    SingletonDemo3 sc = new SingletonDemo3();
                    instance = sc;
                }
            }
        }

        return instance;
    }

    private SingletonDemo3() {
    }
}


/**
 * 防止反射和反序列化漏洞
 */
class SingletonDemo6 implements Serializable {
    private static SingletonDemo6 instance;

    private SingletonDemo6() {
        // 防止反射
        if (instance != null) {
            // 通过手动抛出异常，避免通过反射创建多个单例对象
            throw new RuntimeException("只能创建一个对象");
        }
    }

    // 反序列化时，如果定义了readResolve()则直接返回此方法指定的对象。而不需要再单独创建新对象！
    private Object readResolve() throws ObjectStreamException {
        return instance;
    }

    public static void main(String[] args) throws Exception {
        // 通过反射的方式直接调用私有构造器
        Class<SingletonDemo6> clazz = (Class<SingletonDemo6>) Class.forName("com.bjsxt.singleton.SingletonDemo6");
        Constructor<SingletonDemo6> c = clazz.getDeclaredConstructor(null);
        c.setAccessible(true);
        SingletonDemo6 s3 = c.newInstance();

        // 通过反序列化的方式构造多个对象
        FileOutputStream fos = new FileOutputStream("d:/a.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s3);
        oos.close();
        fos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/a.txt"));
        s3 = (SingletonDemo6) ois.readObject();
        ois.close();
    }
}


class Singleton01 {
    //类初始化时，不初始化这个对象（延时加载，真正用的时候再创建）
    private static Singleton01 instance;

    private Singleton01() {
    }

    //方法同步，调用效率低
    public static synchronized Singleton01 getInstance() {
        if (instance == null) {
            instance = new Singleton01();
        }
        return instance;
    }
}