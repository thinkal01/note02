package com.note.old.beanutils;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Demo01 {
    @Test
    public void fun1() throws Exception {
        String className = "cn.itcast.domain.Person";
        Class clazz = Class.forName(className);
        Object bean = clazz.newInstance();

        BeanUtils.setProperty(bean, "name", "张三");
        BeanUtils.setProperty(bean, "age", "23");
        BeanUtils.setProperty(bean, "gender", "男");
        BeanUtils.setProperty(bean, "xxx", "XXX");

        String age = BeanUtils.getProperty(bean, "age");
        System.out.println(age);
        System.out.println(bean);
    }

    /*
     * 把map的数据封装到一个javabean中,要求map的key与bean的属性名相同
     */
    @Test
    public void fun2() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", "zhangSan");
        map.put("password", "123");

        User user = new User();
        BeanUtils.populate(user, map);

        System.out.println(user);
    }

    @Test
    public void fun3() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "zhangSan");
        map.put("password", "123");

        /*
         * request.getParameterMap();
         */
        User user = CommonUtils.toBean(map, User.class);
        System.out.println(user);
    }
}
