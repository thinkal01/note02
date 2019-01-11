package com.note.common.springEvent.listener;

import com.note.common.springEvent.event.EmailEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmailNotifier implements ApplicationListener {
    // 该方法会在容器发生事件时自动触发
    public void onApplicationEvent(ApplicationEvent evt) {
        // 只处理EmailEvent，模拟发送email通知...
        if (evt instanceof EmailEvent) {
            EmailEvent emailEvent = (EmailEvent) evt;
            System.out.println("需要发送邮件的接收地址  " + emailEvent.getAddress());
            System.out.println("需要发送邮件的邮件正文  " + emailEvent.getText());
        } else {
            // 其他事件不作任何处理
            System.out.println("其他事件：" + evt);
        }
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-event.xml");
        // 创建一个ApplicationEvent对象
        EmailEvent ele = new EmailEvent("test", "spring_test@163.com", "this is a test");
        // 发布容器事件
        ctx.publishEvent(ele);
    }
}