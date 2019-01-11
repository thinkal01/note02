package com.note.base;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class System01 {
    /**
     * System:类中的方法和属性都是静态的
     * 给系统设置一些属性信息。这些信息是全局，其他程序都可以使用。
     * System.setProperty("myclasspath", "c:\myclass");
     */
    @Test
    public void listProps() {
        // 列出系统的全部属性
        System.getProperties().list(System.out);
        // 获取用指定键描述的系统属性,def表示默认信息。
        // static String getProperty(String key, String def)
        System.out.println("系统版本：" + System.getProperty("os.name") + System.getProperty("os.version") + System.getProperty("os.arch"));
        System.out.println("系统用户：" + System.getProperty("user.name"));
        System.out.println("当前用户目录：" + System.getProperty("user.home"));
        System.out.println("当前用户工作目录：" + System.getProperty("user.dir"));
        // 路径分隔符
        System.getProperty("file.separator");
    }

    public static void main(String args[]) {
        long startTime = System.currentTimeMillis();    // 取得开始计算之前的时间
        int sum = 0;            // 声明变量
        for (int i = 0; i < 30000000; i++) {    // 执行累加操作
            sum += i;
        }
        long endTime = System.currentTimeMillis();    // 取得计算之后的时间
        // 结束时间减去开始时间
        System.out.println("计算所花费的时间：" + (endTime - startTime) + "毫秒");
    }

    public void test() {
        // 终止虚拟机的运行.
        // 对于发生了异常情况而想终止虚拟机的运行,传递一个非0数值,对于正常情况下退出系统传递0值;
        // 该方法实际调用的是Runtime.getRuntime().exit(int status)；
        // static void exit(int status)


        // 强制性释放空间
        // 实际上调用了 Runtime中的gc()方法;
        // Runtime.getRuntime().exec("notepad ");
        System.gc();

        // 获得指定的环境变量;
        // String getenv(String name)
    }

    public void runtimeFun() {
        Runtime run = Runtime.getRuntime();    // 通过Runtime类的静态方法进行实例化操作
        System.out.println("JVM最大内存量：" + run.maxMemory());    // 最大的内存，根据会有所不同
        System.out.println("JVM空闲内存量：" + run.freeMemory());    // 取得程序运行的空闲内存
        String str = "Hello " + "World" + "!!!" + "\t" + "Welcome " + "To " + "MLDN" + "~";
        for (int x = 0; x < 1000; x++) {
            str += x;            // 循环修改内容，会产生多个垃圾
        }
        System.out.println("操作String之后的,JVM空闲内存量：" + run.freeMemory());
        run.gc();        // 进行垃圾收集，释放空间
        System.out.println("垃圾回收之后的,JVM空闲内存量：" + run.freeMemory());
    }

    public void test01() {
        /* Runtime类对象的使用：
         *Runtime类使用了单例设计模式。*/
        Runtime run = Runtime.getRuntime(); // 取得Runtime类的实例化对象
        Process p = null;    // 定义进程变量
        try {
            p = run.exec("notepad.exe f:\0.txt"); // 调用本机程序，此方法需要异常处理
        } catch (Exception e) {
        }
        try {
            Thread.sleep(5000); // 让此线程存活5秒
        } catch (Exception e) {
        }
        p.destroy();    // 结束此进程
    }

    @Test
    public void test02() throws IOException {
        String cmd = "cmd " + "/c " + "ipconfig/all";
        Process process = Runtime.getRuntime().exec(cmd);
        Scanner scanner = new Scanner(process.getInputStream(), "gbk");
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}