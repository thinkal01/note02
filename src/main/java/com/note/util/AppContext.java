package com.note.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Locale;

public class AppContext implements ApplicationContextAware {
    private static ApplicationContext context;

    /* Spring容器会检测容器中所有Bean，如果发现某个Bean实现了ApplicationContextAware接口，
    Spring容器会在创建该Bean之后，自动调用该方法，调用该方法时，会将容器本身作为参数传给该方法。*/
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext get() {
        return context;
    }

    public String getMessage(String value) {
        return context.getMessage("info", new String[]{value}, Locale.getDefault(Locale.Category.FORMAT));
    }
}
