package com.note.test;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.fail;

public class Test01 {

    @Test
    public void test() throws Exception {
        fail("Not yet implemented");


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
}

