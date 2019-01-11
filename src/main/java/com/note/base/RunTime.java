package com.note.base;

import java.io.*;

public abstract class RunTime {    // 需求：请给我计算出一段代码的运行时间
    public long getTime() {
        long start = System.currentTimeMillis();

        code();

        long end = System.currentTimeMillis();
        return end - start;
    }

    public abstract void code();
}

class CopyDemo extends RunTime {
    @Override
    public void code() {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("a.avi"));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("b.avi"))) {
            byte[] bys = new byte[1024];
            int len;
            while ((len = bis.read(bys)) != -1) {
                bos.write(bys, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
