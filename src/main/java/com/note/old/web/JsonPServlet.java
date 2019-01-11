package com.note.old.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonPServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //编码
        response.setContentType("application/json;charset=UTF-8");
        //1 请求参数，获得函数名称
        String callback = request.getParameter("callback");
        //2 数据
        String jsonStr = "{\"success\":\"用户操作成功\",\"info\":\"提示信息\"}";
        //3发送浏览器，通知tomcat1，执行执行函数  print("函数(参数)");
        response.getWriter().print(callback + "(" + jsonStr + ")");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
