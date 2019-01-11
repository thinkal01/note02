package com.note.controller;

import org.junit.Test;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpController implements HttpRequestHandler {

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //给Request设置值，在页面进行回显
        request.setAttribute("hello", "这是HttpRequestHandler！");
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/jsps/index.jsp").forward(request, response);
    }

    @Test
    public void test() {
        // 随时都能取到当前请求的request对象
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
    }

}
