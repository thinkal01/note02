package com.note.common.activemq.consumer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @描述: ActiveMQ测试启动类
 */
public class MQConsumer {
    private static final Log log = LogFactory.getLog(MQConsumer.class);

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:activemq/spring-context.xml");
            context.start();
        } catch (Exception e) {
            log.error("==>MQ context start error:", e);
            System.exit(0);
        }
    }
}
