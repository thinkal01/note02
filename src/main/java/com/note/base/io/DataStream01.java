package com.note.base.io;

import java.io.*;

public class DataStream01 {
    private static void read() throws IOException {
        // 创建数据输入流对象
        DataInputStream dis = new DataInputStream(new FileInputStream("dos.txt"));

        // 读数据
        byte b = dis.readByte();
        short s = dis.readShort();
        int i = dis.readInt();
        long l = dis.readLong();
        float f = dis.readFloat();
        double d = dis.readDouble();
        char c = dis.readChar();
        boolean bb = dis.readBoolean();

        // 释放资源
        dis.close();

        System.out.println(b);
        System.out.println(s);
        System.out.println(i);
        System.out.println(l);
        System.out.println(f);
        System.out.println(d);
        System.out.println(c);
        System.out.println(bb);
    }

    private static void write() throws IOException {
        // 创建数据输出流对象
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("dos.txt"));

        // 写数据了
        dos.writeByte(10);
        dos.writeShort(100);
        dos.writeInt(1000);
        dos.writeLong(10000);
        dos.writeFloat(12.34F);
        dos.writeDouble(12.56);
        dos.writeChar('a');
        dos.writeBoolean(true);

        // 释放资源
        dos.close();
    }

    public static void byteStream() throws IOException {
        // 写数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for (int x = 0; x < 10; x++) {
            baos.write(("hello" + x).getBytes());
        }

        // 释放资源
        // 通过查看源码我们知道这里什么都没做，所以根本不需要close()
        // baos.close();

        byte[] bys = baos.toByteArray();

        // 读数据
        ByteArrayInputStream bais = new ByteArrayInputStream(bys);

        int by;
        while ((by = bais.read()) != -1) {
            System.out.print((char) by);
        }

        // bais.close();
    }
}
