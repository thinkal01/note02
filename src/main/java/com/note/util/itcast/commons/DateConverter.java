package com.note.util.itcast.commons;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 把String转换成java.util.Date的类型转换器
 */
public class DateConverter implements Converter {
    @SuppressWarnings("rawtypes")
    @Override
    public Object convert(Class type, Object value) {
        // 判断type是否为Date类型，如果不是，本转换器不理睬
        if (type != java.util.Date.class) {
            return null;
        }

        //如果要转换成值为null，那么直接返回null
        if (value == null) return null;

        //如果要转换的值不是String
        if (!(value instanceof String)) {
            return null;
        }

        String val = (String) value;
        // 使用SimpleDateFormat进行转换
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(val);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
