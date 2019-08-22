package com.note.old.web;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 演示响应字节数据
         */
        // 把一张图片读取到字节数组中
        String path = "F:/白冰.jpg";
        FileInputStream in = new FileInputStream(path);
        // byte[] bytes = IOUtils.toByteArray(in);//读取输入流内容的字节到字节数组中。
        // response.getOutputStream().write(bytes);

        IOUtils.copy(in, response.getOutputStream());

        String userAgent = request.getHeader("ItemUser-Agent");//获取名为User-Agent的请求头！
        /*
         * Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36
         */
        // 是否包含Chrome，如果包含，说明用户使用的是google浏览器
        if (userAgent.toLowerCase().contains("chrome")) {
            System.out.println("你用的是谷歌");
        } else if (userAgent.toLowerCase().contains("firefox")) {
            System.out.println("你用的是火狐");
        } else if (userAgent.toLowerCase().contains("msie")) {
            System.out.println("你用的是IE");
        }

        /**
         * 使用Referer请求头，来防盗链
         */
        String referer = request.getHeader("Referer");
        if (referer == null || !referer.contains("localhost")) {
            response.sendRedirect("http://www.baidu.com");
        } else {
            System.out.println("hello!");
        }
    }
}
