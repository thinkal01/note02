package com.note.base.io;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class LineNumberReader01 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("IO流_2.txt");
        LineNumberReader lnr = new LineNumberReader(fr);
        String line;
        // 设置当前行号
        lnr.setLineNumber(100);
        while ((line = lnr.readLine()) != null) {
            // 获得当前行号
            System.out.println(lnr.getLineNumber() + ":" + line);
        }
        lnr.close();
    }
}