package com.note.common.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 将json字符串转为bean对象
 */
public class Trans2Bean {

    public static void main(String[] args) {
        parseToBean();
    }

    /**
     * JSONObject parseObject(String text)的使用
     */
    private static void parseStringToJsonObject() {
        System.out.println("parseObject-------------------------------------start");
        //知道它肯定是一个对象数据，那么可以这么用
        String bean_json = "{'id':1,'isdead':true,'birth':1473153533840,'name':'赵飞','age':68}";
        // String jsonStr1 = "{'id':1,'isdead':true,'birth':'2016-09-01','name':'赵飞','age':68}";
        // String jsonStr1 = "{'id':1,'isdead':true,'birth':1473153533840,'name':'赵飞','age':68}";

        JSONObject jsonObj = JSON.parseObject(bean_json);//转为对应的JSONObject对象，可使用JSONObject的一些api
        System.out.println("id=" + jsonObj.getLongValue("id"));
        System.out.println("name=" + jsonObj.getString("name"));
        System.out.println("age=" + jsonObj.getIntValue("age"));
        System.out.println("birth=" + jsonObj.getDate("birth"));
        System.out.println("isdead=" + jsonObj.getBooleanValue("isdead"));
        // 如果有其他嵌套属性可以继续这么取
        // System.out.println(jsonObj.getJSONArray(""));
        // System.out.println(jsonObj.getJSONObject(""));
        System.out.println("parseObject-------------------------------------end\n");
    }

    /**
     * FastJson在映射实体上非常棒， json有的Key，实体没有，依然不影响解析
     * T parseObject(String text, Class clazz)的使用
     */
    private static void parseToBean() {
        System.out.println("parseToBean-------------------------------------start");
        String bean_json = "{'id':1,'isdead':true,'birth':1473153533840,'name':'赵飞','age':68}";
        User user = JSON.parseObject(bean_json, User.class);
        System.out.println("user=" + user);
        System.out.println("parseToBean-------------------------------------end\n");
    }

}
