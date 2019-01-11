package com.note.util.itcast.commons;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import java.util.Map;
import java.util.UUID;

/**
 * 小工具
 */
public class CommonUtils {
    /**
     * 返回一个不重复的字符串
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * 把map转换成对象
     *
     * @param map
     * @param clazz
     * @return 把Map转换成指定类型
     */
    @SuppressWarnings("rawtypes")
    public static <T> T toBean(Map map, Class<T> clazz) {
        try {
            /*
             * 1. 通过参数clazz创建实例
             */
            T bean = clazz.newInstance();
            // 把DateConverter注册到BeanUtils组件中
            ConvertUtils.register(new DateConverter(), java.util.Date.class);
            // 把Map中的数据放到bean对象中, Map的key必须与bean的属性同名
            BeanUtils.populate(bean, map);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
