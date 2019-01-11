package com.note.old.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //编码
        response.setContentType("application/json;charset=UTF-8");

        //1 获得请求方式
        String method = request.getMethod();
        System.out.println("请求方式： " + method);

        //2 获得请求参数
        String username = request.getParameter("username");
        System.out.println(username);

        //3 服务器返回数据--json  (框架：jsonlib  fastjson flex 等)
        String jsonStr = "{\"success\":\"用户操作成功\",\"info\":\"提示信息\"}";
        //String jsonStr = "{'success':'用户操作成功','info':'提示信息'}";  //非法格式
        response.getWriter().print(jsonStr);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
