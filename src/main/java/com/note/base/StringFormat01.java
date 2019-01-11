package com.note.base;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class StringFormat01 {
    @Test
    public void testResourceBundle() {
        ResourceBundle rb = ResourceBundle.getBundle("Message");    // 找到资源文件，不用编写后缀
        System.out.println("内容：" + rb.getString("info"));    // 从资源文件中取得内容

        Locale zhLoc = new Locale("zh", "CN");        // 表示中国地区
        Locale enLoc = new Locale("en", "US");        // 表示美国地区
        Locale frLoc = new Locale("fr", "FR");        // 表示法国地区
        // 找到中文的属性文件，需要指定中文的Locale对象
        ResourceBundle zhrb = ResourceBundle.getBundle("Message", zhLoc);
        // 找到英文的属性文件，需要指定英文的Locale对象
        ResourceBundle enrb = ResourceBundle.getBundle("Message", enLoc);
        // 找到法文的属性文件，需要指定法文的Locale对象
        ResourceBundle frrb = ResourceBundle.getBundle("Message", frLoc);
        // 依次读取各个属性文件的内容，通过键值读取，此时的键值名称统一为info
        String str1 = zhrb.getString("info");
        String str2 = enrb.getString("info");
        String str3 = frrb.getString("info");
        System.out.println("中文：" + MessageFormat.format(str1, "李兴华"));
        System.out.println("英语：" + MessageFormat.format(str2, "LiXingHua"));
        System.out.println("法语：" + MessageFormat.format(str3, "LiXingHua"));
    }

    @Test
    public void testPrint() throws FileNotFoundException {
        PrintStream ps;
        // 使用FileOuputStream实例化，输出到文件中
        ps = new PrintStream(new FileOutputStream(new File("e:" + File.separator + "test.txt")));
        String name = "李兴华";
        int age = 30;
        float score = 990.356f;
        char sex = 'M';
        ps.print("hello ");
        ps.println("world");
        ps.printf("姓名：%s；年龄：%s；成绩：%s；性别：%s", name, age, score, sex);
        ps.printf("姓名：%s；年龄：%d；成绩：%f；性别：%c", name, age, score, sex);
        ps.close();
    }

    @Test
    public void messageFormat01() {
        // MessageFormat支持占位符重用,而String.format可以自动转换大小写
        String s = MessageFormat.format("{0}今年{1}岁", "我", 24);
    }

    @Test
    public void springMessageSource() {
        // 实例化ApplicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-message.xml");
        // 使用getMessage()方法获取本地化消息。
        // Locale的getDefault方法返回计算机环境的默认Locale
        String hello = ctx.getMessage("hello", new String[]{"孙悟空"}, Locale.getDefault(Locale.Category.FORMAT));
        String now = ctx.getMessage("now", new Object[]{new Date()}, Locale.getDefault(Locale.Category.FORMAT));
        // 打印出两条本地化消息
        System.out.println(hello);
        System.out.println(now);
    }
}
