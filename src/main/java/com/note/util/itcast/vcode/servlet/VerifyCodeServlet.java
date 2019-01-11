package com.note.util.itcast.vcode.servlet;

import com.note.util.itcast.vcode.utils.VerifyCode;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VerifyCodeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerifyCode vc = new VerifyCode();
        //获取一次性验证码图片
        BufferedImage image = vc.getImage();
        // 把图片写到指定流中,该方法必须在getImage()方法之后调用
        VerifyCode.output(image, response.getOutputStream());
        // 把图片上的文本保存到session中，为LoginServlet验证做准备
        request.getSession().setAttribute("vCode", vc.getText());
    }
}
