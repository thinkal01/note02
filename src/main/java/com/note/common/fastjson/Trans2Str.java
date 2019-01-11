package com.note.common.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * java对象转json字符串
 * 基本bean和集合对象
 * <p>
 * String toJSONString(Object object)
 * Object toJSON(Object javaObject);
 * <p>
 * JSON.toString() -> JSON.toJSONString()
 *
 * @Override public String toString() {
 * return toJSONString();
 * }
 */
public class Trans2Str {

    public static void main(String[] args) {
        bean2JSONString();
        list2JSONString();
    }

    private static void bean2JSONString() {
        User user = new User(1, "张飞", new Date(), 37, true);
        System.out.println("user=" + JSON.toJSONString(user));//数据输出
        System.out.println("user.fmt=" + JSON.toJSONString(user, true));//格式化输出
    }

    private static void list2JSONString() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "张飞", new Date(), 37, true));
        users.add(new User(2, "赵云", new Date(), 35, true));
        System.out.println("users=" + JSON.toJSONString(users));//数据输出
        System.out.println("users.fmt=" + JSON.toJSONString(users, true));//格式化输出
    }


    /*
    复杂数据类型map
     * {"age":24,
     * "girlInfo":{"age":"23","name":"xiaohong"},
     * "hobby":["爬山","骑车","旅游"],
     * "sex":"男","username":"zhangsan"}
     */
    public static void Complexdata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "zhangsan");
        map.put("age", 24);
        map.put("sex", "男");

        // map集合
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("name", "xiaohong");
        temp.put("age", "23");
        map.put("girlInfo", temp);

        // list集合
        List<String> list = new ArrayList<>();
        list.add("爬山");
        list.add("骑车");
        list.add("旅游");
        map.put("hobby", list);
        String jsonString = JSON.toJSONString(map);
        System.out.println("复杂数据类型:" + jsonString);
    }

    // 格式日期
    public static void DateFormate(Date date) {
        System.out.println("输出毫秒值：" + JSON.toJSONString(date));
        System.out.println("默认格式为:" + JSON.toJSONString(date, SerializerFeature.WriteDateUseDateFormat));
        System.out.println("自定义日期：" + JSON.toJSONStringWithDateFormat(date, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat));
        // 全局修改日期格式
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        // JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
        // "2017-08-16 10:51:47.763"
        System.out.println(JSON.toJSONStringWithDateFormat(date, "yyyy-MM-dd HH:mm:ss.SSS"));
    }

}