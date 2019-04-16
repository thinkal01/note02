package com.note.test;

import com.note.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class Test01 {

    @Test
    public void test() throws Exception {
        // fail("Not yet implemented");
        int[] i = new int[10];
        i[0] = 1;

        int i1 = DateUtil.diffDate("2005-9-10","2005-10-20" );
    }

    @Test
    public void testTime() {
        Tools tool = new Tools();
        //定义execute方法
        tool.testTime(() -> {
            //这里可以加放一个或多个要测试运行时间的方法
            for (int i = 0; i < 100000; i++) {
                System.out.println(i);
            }
        });
    }

    class Tools {
        /**
         * 测试函数使用时间，通过定义CallBack接口的execute方法
         */
        public void testTime(CallBack callBack) {
            long begin = System.currentTimeMillis(); //测试起始时间
            callBack.execute(); ///进行回调操作
            long end = System.currentTimeMillis(); //测试结束时间
            System.out.println("[use time]:" + (end - begin)); //打印使用时间
        }
    }

    public interface CallBack {
        //执行回调操作的方法
        void execute();
    }

    public static void main(String[] args) {
        //GBK编码格式源码路径
        String srcDirPath = "G:\\Files\\百度云\\spring源码深度解析\\课件\\代码\\springmvc-annotation\\src\\main\\java\\com";
        //转为UTF-8编码格式源码路径
        String utf8DirPath = "G:\\Files\\百度云\\spring源码深度解析\\课件\\代码\\springmvc-annotation\\src\\main\\java\\com-utf8";
        //获取所有java文件
        Collection<File> javaGbkFileCol = FileUtils.listFiles(new File(srcDirPath), new String[]{"java"}, true);
        for (File javaGbkFile : javaGbkFileCol) {
            //UTF8格式文件路径
            String utf8FilePath = utf8DirPath + javaGbkFile.getAbsolutePath().substring(srcDirPath.length());
            //使用GBK读取数据，然后用UTF-8写入数据
            try {
                FileUtils.writeLines(new File(utf8FilePath), "UTF-8", FileUtils.readLines(javaGbkFile, "GBK"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}