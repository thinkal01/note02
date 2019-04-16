package com.note.base.jvm.bytecode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

/**
 * 使用javap -verbose MyTest3.class
 * test()方法输出信息如下
 * locals=4, args_size=1
 * 参数数量为1，这里是因为对于实例方法，编译后的字节码会在方法入参多生成一个this
 * 局部变量有4个，分别为方法入参的this，is，serverSocket以及ex，虽然这里会捕获多个异常类，但实际至多只会有一个异常类被捕获到
 */
public class MyTest3 {
    public void test() {
        try {
            InputStream is = new FileInputStream("test.txt");

            ServerSocket serverSocket = new ServerSocket(9999);
            serverSocket.accept();
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } catch (Exception ex) {

        } finally {
            System.out.println("finally");
        }


    }
}
