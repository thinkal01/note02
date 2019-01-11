package com.note.old.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VerifyCodeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
         * 1. 生成图片
         * 2. 保存图片上的文本到session域中
         * 3. 把图片响应给客户端
         */
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        request.getSession().setAttribute("session_vcode", vc.getText());//保存图片上的文本到session域
        VerifyCode.output(image, response.getOutputStream());
    }
}
