package com.note.old.web;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 图片
 */
public class VerifyCodeTest {
    public static void main(String[] args) throws IOException {
        /*
         * Image、ImageIO、BufferedImage、Icon、ImageIcon
         * 1. 创建图片缓冲区
         * 2. 设置其宽高
         * 3. 得到这个图片的绘制环境（得到画笔）
         * 4. 保存起来
         */
        BufferedImage bi = new BufferedImage(150, 70, BufferedImage.TYPE_INT_RGB);

        // 得到它的绘制环境（这张图片的笔）
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setColor(Color.WHITE);//设置环境颜色
        g2.fillRect(0, 0, 150, 70);//填充矩形,从0,0点开始，宽150，高70(其实就是设置背景色)
        g2.setColor(Color.RED);
        g2.drawRect(0, 0, 150 - 1, 70 - 1);

        g2.setFont(new Font("宋体", Font.BOLD, 18));//设置字体
        g2.setColor(Color.BLACK);//设置颜色

        g2.drawString("HelloWorld", 3, 50);//向图片上写字符串,其中3,50表示x,y轴的坐标

        ImageIO.write(bi, "JPEG", new FileOutputStream("F:/a.jpg"));//保存图片
    }

    @Test
    public void fun1() throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage bi = verifyCode.getImage();//随机的！
        System.out.println(verifyCode.getText());//打印图片上的文本内容
        VerifyCode.output(bi, new FileOutputStream("F:/b.jpg"));
    }
}
