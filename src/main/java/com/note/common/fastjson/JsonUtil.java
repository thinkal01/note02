package com.note.common.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * [JSON工具类]
 */
public class JsonUtil {
    /**
     * [字符串转对象]
     *
     * @param jsonString
     * @param clazz
     */
    public static <T> T jsonToObject(String jsonString, Class<T> clazz) {
        if (jsonString == null || "".equals(jsonString)) {
            return null;
        }
        return JSON.parseObject(jsonString, clazz);
    }

    /**
     * [对象转json]
     *
     * @param object
     */
    public static String objectToJson(Object object) {
        if (object == null) {
            return "";
        }
        return JSON.toJSONString(object);
    }

    /**
     * @param jsonString
     */
    public static List<Object> jsonToList(String jsonString) {
        if (jsonString == null || "".equals(jsonString)) {
            return null;
        }
        return JSON.parseObject(jsonString, new TypeReference<List<Object>>() {
        });
    }

    /**
     * [字符串转map]
     *
     * @param jsonString
     */
    public static Map<String, Object> jsonToMap(String jsonString) {
        if (jsonString == null || "".equals(jsonString)) {
            return null;
        }

        return JSON.parseObject(jsonString, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * [字符串转化为复杂对象]
     *
     * @param jsonString
     */
    public static List<Map<String, Object>> jsonToMixedType(String jsonString) {
        if (jsonString == null || "".equals(jsonString)) {
            return null;
        }

        return JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    /**
     * [把日期转化成标准格式的字符串]
     *
     * @param date
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public static String dateFormatToJson(Date date) {
        if (date == null) {
            return "";
        }

        return JSON.toJSONString(date, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * [日期转化成指定格式的字符串]
     *
     * @param date
     * @param format
     */
    public static String dateFormatToJson(Date date, String format) {
        if (date == null) {
            return "";
        }
        return JSON.toJSONStringWithDateFormat(date, format, SerializerFeature.WriteDateUseDateFormat);
    }
}