package com.note.old.itcast_framework.beanfactory.web.utils;

import com.note.old.itcast_framework.beanfactory.BeanFactory;

import javax.servlet.ServletContext;

public class IocUtils {
    public static final String KEY = "cn.itcast.beanfactory.BeanFactory.key";

    public static void add(ServletContext sc, BeanFactory factory) {
        sc.setAttribute(KEY, factory);
    }

    public static BeanFactory get(ServletContext sc) {
        return (BeanFactory) sc.getAttribute(KEY);
    }

    public static Object getBean(ServletContext sc, String id) {
        return get(sc).getBean(id);
    }
}
