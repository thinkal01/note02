package com.note.base.io;

import com.note.util.CommonUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriter01 {
    public void test01() throws IOException {
        // 如果文件不存在，则会自动创建。
        // 如果文件存在，则会被覆盖。
        // 如果构造函数中加入true，可以实现对文件进行追加
        FileWriter fw = new FileWriter("demo.txt", true);

        // 和输出流不一样，如果文件不存在，则创建，如果文件存在，则不创建。
        File file = new File("file.txt");

        // 调用Writer对象中的write(string) 方法，写入数据。
        // 其实数据写入到临时存储缓冲区中。
        fw.write("abcde" + CommonUtil.NEW_LINE + "hahaha");

        // 进行刷新，将数据直接写到目的地中。
        fw.flush();

        // 关闭流，关闭资源。在关闭前会先调用flush刷新缓冲中的数据到目的地。
        fw.close();
    }
}
