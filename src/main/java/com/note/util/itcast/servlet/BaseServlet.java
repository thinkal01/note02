package com.note.util.itcast.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

/**
 * BaseServlet用作其它Servlet的父类
 * 一个类多个请求处理方法，每个请求处理方法的原型与service相同！ 原型 = 返回值类型 + 方法名称 + 参数列表
 */
public class BaseServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");//处理响应编码

        /**
         * 1. 获取method参数，它是用户想调用的方法
         */
        String methodName = request.getParameter("method");
        if (methodName == null || methodName.isEmpty()) {
            throw new ServletException("没有找到method参数！");
        }

        Method method = null;
        /**
         * 2. 通过方法名称获取Method对象
         */
        try {
            // 获取方法参数类型
            Class[] types = {HttpServletRequest.class, HttpServletResponse.class};
            method = this.getClass().getMethod(methodName, types);
        } catch (Exception e) {
            throw new RuntimeException("您要调用的方法：" + methodName + "不存在！", e);
        }

        /**
         * 3. 通过method对象来调用它
         * return "r:/index.jsp";
         * return "d:/WEB-INF/files/a.rmvb";
         */
        try {
            String result = (String) method.invoke(this, request, response);
            if (result != null && !result.trim().isEmpty()) {//如果请求处理方法返回不为空
                int index = result.indexOf(":");//获取第一个冒号的位置
                if (index == -1) {//如果没有冒号，使用转发
                    request.getRequestDispatcher(result).forward(request, response);
                } else {//如果存在冒号
                    String start = result.substring(0, index);//分割出前缀
                    String path = result.substring(index + 1);//分割出路径
                    if (start.equals("f")) {//前缀为f表示转发
                        request.getRequestDispatcher(path).forward(request, response);
                    } else if (start.equals("r")) {//前缀为r表示重定向
                        response.sendRedirect(request.getContextPath() + path);
                    } else {
                        throw new RuntimeException("您指定的操作：" + start + "，当前版本还不支持！");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
