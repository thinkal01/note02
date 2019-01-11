package com.note.base.io;

import com.note.base.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

public class IO02 {
    public static void main(String[] args) throws IOException {
        File dir = new File("c:\\partfiles");
        mergeFile(dir);
    }

    // 合并碎片文件
    public static void mergeFile(File dir) throws IOException {
        // 获取指定目录下的配置文件对象
        File[] files = dir.listFiles(new SuffixFilter(".properties"));
        if (files.length != 1)
            throw new RuntimeException(dir + ",该目录下没有properties扩展名的文件或者不唯一");

        //记录配置文件对象
        File confile = files[0];
        //获取该文件中的信息
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(confile);
        prop.load(fis);
        String filename = prop.getProperty("filename");
        int count = Integer.parseInt(prop.getProperty("partcount"));

        //获取该目录下的所有碎片文件
        File[] partFiles = dir.listFiles(new SuffixFilter(".part"));
        if (partFiles.length != (count - 1)) {
            throw new RuntimeException("碎片文件不符合要求，个数不对!应该" + count + "个");
        }

        //将碎片文件和流对象关联,并存储到集合中。
        ArrayList<FileInputStream> al = new ArrayList<>();
        for (int x = 0; x < partFiles.length; x++) {
            al.add(new FileInputStream(partFiles[x]));
        }

        //将多个流合并成一个序列流。
        Enumeration<FileInputStream> en = Collections.enumeration(al);
        SequenceInputStream sis = new SequenceInputStream(en);
        FileOutputStream fos = new FileOutputStream(new File(dir, filename));
        byte[] buf = new byte[1024];
        int len = 0;

        while ((len = sis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }

        fos.close();
        sis.close();
    }

    public static void readObj() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.object"));
        //对象的反序列化。
        Person p = (Person) ois.readObject();
        ois.close();
    }

    public static void writeObj() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.object"));
        //对象序列化。 被序列化的对象必须实现Serializable接口。
        oos.writeObject(new Person("小强", 30));
        oos.close();
    }

    /*
     * RandomAccessFile
     * 看这个类名字，不是io体系中的子类。
     * 特点：
     * 1，该对象即能读，又能写。
     * 2，该对象内部维护了一个byte数组，并通过指针可以操作数组中的元素，
     * 3，可以通过getFilePointer方法获取指针的位置，通过seek方法设置指针的位置。
     * 4，其实该对象就是将字节输入流和输出流进行了封装。
     * 5，该对象的源或者目的只能是文件。通过构造函数就可以看出。
     */
    public static void randomWrite() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("ranacc.txt", "rw");
        //往指定位置写入数据。
        raf.seek(3 * 8);
        raf.write("哈哈".getBytes());
        raf.writeInt(108);
        raf.close();
    }

    public static void readFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("ranacc.txt", "r");
        //通过seek设置指针的位置。
        raf.seek(1 * 8);//随机的读取。只要指定指针的位置即可。
        byte[] buf = new byte[4];
        raf.read(buf);
        String name = new String(buf);
        int age = raf.readInt();
        System.out.println("pos:" + raf.getFilePointer());
        raf.close();
    }

    //使用RandomAccessFile对象写入一些人员信息，比如姓名和年龄。
    public static void writeFile() throws IOException {
        // *如果文件不存在，则创建，如果文件存在，不创建
        RandomAccessFile raf = new RandomAccessFile("ranacc.txt", "rw");
        raf.write("张三".getBytes());
        raf.writeInt(97);
        raf.close();
    }

    // 管道流
    public void PipedStream() throws IOException {
        PipedInputStream input = new PipedInputStream();
        PipedOutputStream output = new PipedOutputStream();
        input.connect(output);
        new Thread(new Input(input)).start();
        new Thread(new Output(output)).start();
    }

    class Input implements Runnable {
        private PipedInputStream in;

        Input(PipedInputStream in) {
            this.in = in;
        }

        public void run() {
            try {
                byte[] buf = new byte[1024];
                int len = in.read(buf);
                String s = new String(buf, 0, len);
                in.close();
            } catch (Exception e) {
            }
        }
    }

    class Output implements Runnable {
        private PipedOutputStream out;

        Output(PipedOutputStream out) {
            this.out = out;
        }

        public void run() {
            try {
                Thread.sleep(5000);
                out.write("hi，管道来了！".getBytes());
            } catch (Exception e) {
            }
        }
    }

    // DataStream数据流
    public static void readData() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));
        String str = dis.readUTF();
    }

    public static void writeData() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));
        dos.writeUTF("你好");
        dos.close();
    }

    // 字节数组流
    public void ByteArrayStream() {
        ByteArrayInputStream bis = new ByteArrayInputStream("abcedf".getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int ch = 0;
        while ((ch = bis.read()) != -1) {
            bos.write(ch);
        }
        System.out.println(bos.toString());
    }
}