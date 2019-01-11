package com.note.base.io;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

public class SequenceInputStream01 {
    public static void merge() throws IOException {
        // 把ByteArrayStreamDemo.java和DataStreamDemo.java的内容复制到Copy.java中
        InputStream s1 = new FileInputStream("ByteArrayStreamDemo.java");
        InputStream s2 = new FileInputStream("DataStreamDemo.java");
        SequenceInputStream sis = new SequenceInputStream(s1, s2);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Copy.java"));

        byte[] bys = new byte[1024];
        int len;
        while ((len = sis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }

        bos.close();
        sis.close();
    }

    public static void main(String[] args) throws IOException {
        // 需求：把下面的三个文件的内容复制到Copy.java中
        // ByteArrayStreamDemo.java,CopyFileDemo.java,DataStreamDemo.java
        Vector<InputStream> v = new Vector<>();
        InputStream s1 = new FileInputStream("ByteArrayStreamDemo.java");
        InputStream s2 = new FileInputStream("CopyFileDemo.java");
        InputStream s3 = new FileInputStream("DataStreamDemo.java");
        v.add(s1);
        v.add(s2);
        v.add(s3);
        Enumeration<InputStream> en = v.elements();
        SequenceInputStream sis = new SequenceInputStream(en);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Copy.java"));

        byte[] bys = new byte[1024];
        int len;
        while ((len = sis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }

        bos.close();
        sis.close();
    }
}
