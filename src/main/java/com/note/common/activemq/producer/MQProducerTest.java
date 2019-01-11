package com.note.common.activemq.producer;

import com.note.common.activemq.MailParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @描述: ActiveMQ测试启动类 ..
 */
public class MQProducerTest {
    private static final Log log = LogFactory.getLog(MQProducerTest.class);

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:activemq/spring-context.xml");
            context.start();

            MQProducer mqProducer = (MQProducer) context.getBean("mqProducer");
            // 邮件发送
            MailParam mail = new MailParam();
            mail.setTo("laikai26@foxmail.com");
            mail.setSubject("ActiveMQ测试");
            mail.setContent("通过ActiveMQ异步发送邮件！");
            mqProducer.sendMessage(mail);

            context.stop();
        } catch (Exception e) {
            log.error("==>MQ context start error:", e);
            System.exit(0);
        } finally {
            log.info("===>System.exit");
            System.exit(0);
        }
    }
}
