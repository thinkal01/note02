package com.note.base.jvm.classloader;

/**
 * Created by zhouyilin on 2018/4/29.
 *
 * 如果只把MySample编译后的classpath下的class文件删掉，那么MySample会用自定义加载器加载，
 * 而MyCat由系统类加载器加载，由于系统类加载器无法加载MySample，因此打印from MyCat时会报错
 *
 * 如果只把MyCat编译后的classpath下的class文件删掉，那么MySample由系统类加载器加载，
 * 而newInstance时系统类加载器加载MyCat失败，会抛异常
 *
 * 子加载器所加载的类能够访问到父加载器所加载的类
 * 父加载器所加载的类无法访问到子加载器所加载的类
 *
 */
public class Test17 {

    public static void main(String[] args) throws Exception {
        Test16 test16 = new Test16("loader1");
        test16.setPath("/Users/zhouyilin/Desktop/test/");

        Class<?> clazz = test16.loadClass("com.eli.jvm.classloader.MySample");
        System.out.println("class : " + clazz.hashCode());

        // 如果注释掉该行，那么并不会实例化MySample对象，即MySample构造方法不会被调用
        // 因此不会实例化MyCat对象，即没有对MyCat进行主动使用，这里就不会加载MyCat class
        Object obj = clazz.newInstance();
    }
}
