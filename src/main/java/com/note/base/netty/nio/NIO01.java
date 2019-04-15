package com.note.base.netty.nio;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/*
 * nio包在JDK4出现，提供了IO流的操作效率。但是目前还不是大范围的使用。
 * 有空的话了解下，有问题再问我。
 *
 * JDK7的之后的nio：
 * Path:路径
 * Paths:有一个静态方法返回一个路径
 *  public static Path get(URI uri)
 * Files:提供了静态方法供我们使用
    public static long copy(Path source,OutputStream out):复制文件
    public static Path write(Path path,Iterable<? extends CharSequence> lines,Charset cs,OpenOption... options)
 */

public class NIO01 {
    public static void main(String[] args) throws IOException {
        Files.copy(Paths.get("ByteArrayStreamDemo.java"), new FileOutputStream("Copy.java"));
        ArrayList<String> array = new ArrayList<>();
        array.add("hello");
        array.add("world");
        array.add("java");
        Files.write(Paths.get("array.txt"), array, Charset.forName("GBK"));
    }

    @Test
    public void test2() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NIO01.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0, (byte) 'a');
        map.put(3, (byte) 'b');
        randomAccessFile.close();

        // 文件锁
        FileLock fileLock = channel.lock(3, 6, true);
        System.out.println(fileLock.isValid());
        System.out.println(fileLock.isShared());
        fileLock.release();
        randomAccessFile.close();
    }
}
