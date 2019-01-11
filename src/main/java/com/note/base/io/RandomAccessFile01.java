package com.note.base.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFile01 {
    private static void read() throws IOException {
        // 创建随机访问流对象
        RandomAccessFile raf = new RandomAccessFile("raf.txt", "rw");

        int i = raf.readInt();
        // 该文件指针可以通过getFilePointer方法读取，并通过seek方法设置
        System.out.println("当前文件的指针位置是：" + raf.getFilePointer());

        char ch = raf.readChar();
        System.out.println("当前文件的指针位置是：" + raf.getFilePointer());

        String s = raf.readUTF();
        System.out.println("当前文件的指针位置是：" + raf.getFilePointer());

        // 不重头开始，读取a
        raf.seek(4);
        ch = raf.readChar();
    }

    private static void write() throws IOException {
        // 创建随机访问流对象
        RandomAccessFile raf = new RandomAccessFile("raf.txt", "rw");

        // 怎么玩呢?
        raf.writeInt(100);
        raf.writeChar('a');
        raf.writeUTF("中国");

        raf.close();
    }
}
