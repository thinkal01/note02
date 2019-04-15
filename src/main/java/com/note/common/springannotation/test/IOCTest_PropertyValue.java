package com.note.common.springannotation.test;

import com.note.common.springannotation.bean.Person;
import com.note.common.springannotation.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class IOCTest_PropertyValue {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

    @Test
    public void test01() {
        printBeans(applicationContext);
        Person person = (Person) applicationContext.getBean("person");

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        // 获取配置文件中的值
        String property = environment.getProperty("person.nickName");
        applicationContext.close();
    }

    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

}
