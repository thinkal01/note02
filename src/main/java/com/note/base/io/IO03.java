package com.note.base.io;

import org.junit.Test;

import java.io.*;
import java.net.URL;

public class IO03 {

    @Test
    public void testPath() {
        URL resource = Object.class.getResource("/");
        // /E:/Java/workspace/git/note01/note01/target/classes/
        String path = resource.getPath();
    }

    @Test
    public void testFile() {
        System.out.println("pathSeparator：" + File.pathSeparator);
        System.out.println("separator：" + File.separator);
        File f = new File("e:" + File.separator + "test.txt");
        try {
            // 创建文件，如果已存在，则不创建。
            f.createNewFile();
            // f.delete(); // 删除文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testByteArray() {
        String str = "HELLOWORLD";
        ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes());    // 向内存中输出内容
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    // 准备从内存ByteArrayInputStream中读取内容
        int temp = 0;

        while ((temp = bis.read()) != -1) {
            char c = (char) temp;    // 读取的数字变为字符
            bos.write(Character.toLowerCase(c));    // 将字符变为小写
        }

        // 所有的数据就都在ByteArrayOutputStream中
        String newStr = bos.toString();
        try {
            bis.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(newStr);
    }

    @Test
    public void testSystemOut() {
        OutputStream out = System.out;        // 此时的输出流是向屏幕上输出
        try {
            out.write("hello world!!!".getBytes());    // 向屏幕上输出
            // System.out.println(Integer.parseInt("abc"));
            System.setOut(new PrintStream(new FileOutputStream("E:" + File.separator + "red.txt")));    // System.out输出重定向

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            System.setErr(new PrintStream(bos));    // 输出重定向
            System.err.println("李兴华");            // 向内存中输出
            System.out.println(bos);    // 输出内存中的数据
        } catch (Exception e) {
            System.out.println(e);
            System.err.println(e);
        } finally {
            try {
                out.close();    // 关闭输出流
            } catch (IOException e) {
            }
        }
    }

    @Test
    public void testSystemIn() throws IOException {
        InputStream input = System.in;    // 从键盘接收数据
        byte b[] = new byte[5];    // 开辟空间，接收数据
        System.out.print("请输入内容：");    // 提示信息
        int len = input.read(b);    // 接收数据
        System.out.println("输入的内容为：" + new String(b, 0, len));

        StringBuffer buf = new StringBuffer();    // 使用StringBuffer接收数据
        System.out.print("请输入内容：");    // 提示信息
        int temp = 0;        // 接收内容
        while ((temp = input.read()) != -1) {
            char c = (char) temp;    // 将数据变为字符
            if (c == '\n') {
                break;
            }
            buf.append(c);
        }
        System.out.println("输入的内容为：" + buf);

        System.setIn(new FileInputStream("d:" + File.separator + "demo.txt"));    // 设置输入重定向
        input = System.in;    // 从文件中接收数据
        b = new byte[1024];// 开辟空间，接收数据
        len = input.read(b);    //接收
        System.out.println("输入的内容为：" + new String(b, 0, len));

        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));    // 将字节流变为字符流
        String str = null;    // 接收输入内容
        System.out.print("请输入内容：");
        str = bufr.readLine();    // 读取一行数据
        System.out.println("输入的内容为：" + str);

        input.close();    // 关闭输入流
    }

    @Test
    public void testRandomFile() throws Exception {
        File f = new File("e:" + File.separator + "test.txt");    // 指定要操作的文件
        RandomAccessFile rdf = new RandomAccessFile(f, "rw");// 读写模式，如果文件不存在，会自动创建
        String name = null;
        int age = 0;
        name = "zhangsan";
        age = 30;
        rdf.writeBytes(name);
        rdf.writeInt(age);
        name = "lisi";
        age = 31;
        rdf.writeBytes(name);
        rdf.writeInt(age);
        name = "wangwu";
        age = 32;
        rdf.writeBytes(name);
        rdf.writeInt(age);
        rdf.close();
    }

    @Test
    public void testReadRandomFile() throws Exception {
        File f = new File("e:" + File.separator + "test.txt");    // 指定要操作的文件
        RandomAccessFile rdf = new RandomAccessFile(f, "r");// 以只读的方式打开文件
        String name = null;
        int age = 0;
        byte b[] = new byte[8];    // 开辟byte数组
        // 读取第二个人的信息，意味着要空出第一个人的信息
        rdf.skipBytes(12);        // 跳过第一个人的信息
        for (int i = 0; i < b.length; i++) {
            b[i] = rdf.readByte();    // 读取一个字节
        }
        name = new String(b);    // 将读取出来的byte数组变为字符串
        age = rdf.readInt();    // 读取数字
        System.out.println("第二个人的信息 --> 姓名：" + name + "；年龄：" + age);
        // 读取第一个人的信息
        rdf.seek(0);    // 指针回到文件的开头
        for (int i = 0; i < b.length; i++) {
            b[i] = rdf.readByte();    // 读取一个字节
        }
        name = new String(b);    // 将读取出来的byte数组变为字符串
        age = rdf.readInt();    // 读取数字
        System.out.println("第一个人的信息 --> 姓名：" + name + "；年龄：" + age);
        rdf.skipBytes(12);    // 空出第二个人的信息
        for (int i = 0; i < b.length; i++) {
            b[i] = rdf.readByte();    // 读取一个字节
        }
        name = new String(b);    // 将读取出来的byte数组变为字符串
        age = rdf.readInt();    // 读取数字
        System.out.println("第三个人的信息 --> 姓名：" + name + "；年龄：" + age);
        rdf.close();                // 关闭
    }

}