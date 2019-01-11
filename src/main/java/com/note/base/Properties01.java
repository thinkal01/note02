package com.note.base;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.util.*;

/**
 * Properties类对应.properties文件。文件内容是键值对，使用"="或空格隔开。开头是"#"的表示注释
 * Properties类在加载.properties文件时使用的iso8859-1的编码。
 * 如果这个配置文件中有中文就必须要进行转义，使用native2ascii.exe命令操作:
 * native2ascii d:/my.properties d:/my2.properties
 * 使用Properties类中的load(InputStream) 方法可以加载配置文件，使用其中的store(OutputStream) 方法可以保存配置到指定文件。
 */
public class Properties01 {

    public static void main(String[] args) {
        // 1，该集合中的键和值都是字符串类型。
        // 2，集合中的数据可以保存到流中，或者从流获取。
        Properties prop = new Properties();

        // public Object setProperty(String key,String value)：添加元素
        prop.setProperty("张三", "30");
        prop.setProperty("李四", "40");
        prop.setProperty("王五", "50");

        // public Set<String> stringPropertyNames():获取所有的键的集合
        Set<String> set = prop.stringPropertyNames();
        for (String key : set) {
            // public String getProperty(String key):获取元素
            String value = prop.getProperty(key);
            System.out.println(key + "---" + value);
        }
    }

    public static void test() throws IOException {
        //读取这个文件。
        File file = new File("info.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        // 注意：这个文件的数据必须是键值对形式
        FileReader fr = new FileReader(file);

        //创建集合存储配置信息。
        // public void load(Reader reader):把文件中的数据读取到集合中
        Properties prop = new Properties();
        //将流中信息存储到集合中。
        prop.load(fr);
        prop.setProperty("wangwu", "16");
        FileWriter fw = new FileWriter(file);
        prop.store(fw, "配置文件注释");
        // prop.list(System.out);
        fw.close();
        fr.close();
    }

    private static void myStore() throws IOException {
        // 创建集合对象
        Properties prop = new Properties();

        prop.setProperty("林青霞", "27");
        prop.setProperty("武鑫", "30");
        prop.setProperty("刘晓曲", "18");

        // FileOutputStream fos = new FileOutputStream("info.txt");
        Writer w = new FileWriter("name.txt");
        //public void store(Writer writer,String comments):把集合中的数据存储到文件
        prop.store(w, "helloworld");
        w.close();
    }

    //模拟一下load方法。
    public static void myLoad2() throws IOException {
        Properties prop = new Properties();
        BufferedReader bufr = new BufferedReader(new FileReader("info.txt"));
        String line = null;
        while ((line = bufr.readLine()) != null) {
            if (line.startsWith("#"))
                continue;
            String[] arr = line.split("=");
            prop.setProperty(arr[0], arr[1]);
        }
        prop.list(System.out);
        bufr.close();
    }

    /**
     * Spring 提供的 PropertiesLoaderUtils 允许直接通过基于类路径的文件地址加载属性资源
     * 最大的好处就是：实时加载配置文件，修改后立即生效，不必重启
     */
    public void propertiesLoaderUtils() {
        Properties props;
        try {
            props = PropertiesLoaderUtils.loadAllProperties("message.properties");
            for (Object key : props.keySet()) {
                System.out.print(key + ":");
                System.out.println(props.get(key));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 采用ResourceBundle类读取配置信息
     * 优点是：可以以完全限定类名的方式加载资源后，且可以在非Web应用中读取资源文件
     * 缺点：只能加载类src下面的资源文件且只能读取.properties文件。
     * <p>
     * 调用方式：
     * 1.配置文件放在resource源包下，不用加后缀
     * PropertiesUtil.getAllMessage("message");
     * 2.放在包里面的
     * PropertiesUtil.getAllMessage("com.test.message");
     */
    public static List<String> getAllMessage(String propertyName) {
        // 获得资源包
        ResourceBundle rb = ResourceBundle.getBundle(propertyName.trim());
        // 通过资源包拿到所有的key
        Enumeration<String> allKey = rb.getKeys();
        // 遍历key 得到 value
        List<String> valList = new ArrayList<>();
        while (allKey.hasMoreElements()) {
            String key = allKey.nextElement();
            String value = rb.getString(key);
            valList.add(value);
        }
        return valList;
    }

}
