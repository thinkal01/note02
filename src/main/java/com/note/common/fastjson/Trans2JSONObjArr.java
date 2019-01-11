package com.note.common.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.*;

/**
 * json字符串解析为JSONArray
 * <p>
 * Object parse(String text)	通用方法
 * JSONArray parseArray(String text)
 */
public class Trans2JSONObjArr {

    public static void main(String[] args) {
        parseStringToJsonArray();//2.JSONArray
        parseStringToJsonArrayOrJsonObject();//3.JSONArray or JSONObject
    }

    /**
     * String转换 JSONArray
     * 并且去除null
     */
    private void String2JSONArray() {
        String arrayAyy = "[[14,\"小学语文\"],[154,\"美食\"],[72,\"高中物理\"],null,[50,\"高中化学\"],[15,\"小学数学\"],[13\"英语\"],null,[1,\"其他英语培训\"],null]";
        JSONArray array = JSONArray.parseArray(arrayAyy);
        System.out.println(array);
        System.out.println("长度: " + array.size());

        Collection nuCon = new Vector();
        nuCon.add(null);
        array.removeAll(nuCon);

        System.out.println(array);
        System.out.println("长度: " + array.size());

        for (Object obj : array) {
            System.out.println(obj instanceof JSONObject); // true
            System.out.println(obj);
        }
    }

    /**
     * List parseArray(String text, Class clazz)的使用
     */
    private static void parseToArray() {
        String array_json = "[{'id':1,'isdead':true,'birth':'2016-09-01 17:32:22','name':'张飞','age':68},{'id':2,'isdead':false,'birth':'2000-09-01 00:32:22','name':'赵云','age':78}]";
        List<User> users = JSON.parseArray(array_json, User.class);

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println("user[" + i + "]=" + user);
        }
    }

    /**
     * JSONArray parseArray(String text)的使用
     * 反序列化能够自动识别如下日期格式：
     * ISO-8601日期格式
     * yyyy-MM-dd
     * yyyy-MM-dd HH:mm:ss
     * yyyy-MM-dd HH:mm:ss.SSS
     * 毫秒数字
     * 毫秒数字字符串
     * .NET JSON日期格式
     * new Date(198293238)
     */
    private static void parseStringToJsonArray() {
        // date类型解析支持毫秒值，日期字符串，日期时间字符串 2016-09-01 17:32:22 2016-09-01 1473153533840
        String jsonStr1 = "[{'id':1,'isdead':true,'birth':'2016-09-01 17:32:22','name':'张飞','age':68},{'id':2,'isdead':false,'birth':'2000-09-01 00:32:22','name':'赵云','age':78}]";
        // String jsonStr1 = "[{'id':1,'isdead':true,'birth':'2016-09-01','name':'张飞','age':68},{'id':2,'isdead':false,'birth':'2000-09-01 00:32:22','name':'赵云','age':78}]";
        // String jsonStr1 = "[{'id':1,'isdead':true,'birth':'1473153533840','name':'张飞','age':68},{'id':2,'isdead':false,'birth':'1471153533840','name':'赵云','age':78}]";

        JSONArray array = JSON.parseArray(jsonStr1);//转为对应的JSONObject对象，可使用JSONObject的一些api

        //继续获取下一层Array,二维数组
        // array.getJSONArray(index)
        //继续获取下一层JsonObject
        // array.getJSONObject(index)

        //继续获取下一层属性，并转为bean对象
        //array.getObject(index, clazz)
        User u = array.getObject(0, User.class);
        System.out.println("u=" + u);

        //使用增强for循环遍历JSONArray
        for (Object object : array) {
            System.out.println("object=" + object);
        }
    }

    /**
     * 通过fastjson实现字符串与json格式之间的数据转换
     */
    public void setStringToJson() {
        String name1 = "liushishi";
        String name2 = "liuyifei";
        String name3 = "jiangyan";

        List<String> list = new ArrayList<>();
        list.add(name1);
        list.add(name2);
        list.add(name3);

        //将字符串格式转换为json格式
        String jsonObj = JSON.toJSONString(list);
        System.out.println(jsonObj);

        //转换成字符串格式
        List<String> list2 = JSON.parseObject(jsonObj, new TypeReference<List<String>>() {
        });
        System.out.println(list2.get(0));
        System.out.println(list2.get(1));

    }

    /**
     * 通过fastjson实现List<Map>与json格式之间的数据转换
     */
    public void setListMapToJson(User user1, User user2) {
        Map<String, User> map = new HashMap<>();
        map.put("user1", user1);
        map.put("user2", user2);

        //将list的map对象转换为json对象
        String jsonObj = JSON.toJSONString(map);
        System.out.println(jsonObj);

        //将json对象转换额外list的map对象
        Map<String, User> map2 = JSON.parseObject(jsonObj, new TypeReference<Map<String, User>>() {
        });
        User newUser1 = map2.get("user1");
        User newUser2 = map2.get("user2");
        System.out.println(newUser1.getName());
        System.out.println(newUser2.getName());
    }

    private static void obj2JsonObjectOrArray() {
        //1.--------java bean对象转为JSONObject
        User user = new User(1, "张飞", new Date(), 37, true);
        Object obj = JSON.toJSON(user);
        if (obj instanceof JSONObject) {
            System.out.println("obj2 is JSONObject");
        } else {
            System.out.println("obj2 is not JSONObject");
        }

        //2.--------java bean对象转为JSONObject
        List<User> users = new ArrayList<>();
        users.add(new User(1, "张飞", new Date(), 37, true));
        users.add(new User(2, "赵云", new Date(), 35, true));
        Object obj2 = JSON.toJSON(users);

        if (obj2 instanceof JSONArray) {
            System.out.println("obj2 is JSONArray");
        } else {
            System.out.println("obj2 is not JSONArray");
        }
    }

    /**
     * Object parse(String text)的使用
     */
    private static void parseStringToJsonArrayOrJsonObject() {
        String bean_json = "{'id':1,'isdead':true,'birth':1473153533840,'name':'赵飞','age':68}";
        Object object1 = JSON.parse(bean_json);//object可能是JSONArray，可能是JSONObject

        if (object1 instanceof JSONObject) {//使用instanceof进行判断
            System.out.println("object1 is JSONObject");
            JSONObject array = (JSONObject) object1;
        } else {
            //继续使用JSONArray的API操作吧
            System.out.println("object1 is not JSONObject");
        }

        String array_json = "[{'id':1,'isdead':true,'birth':'2016-09-01 17:32:22','name':'张飞','age':68},{'id':2,'isdead':false,'birth':'2000-09-01 00:32:22','name':'赵云','age':78}]";
        Object object2 = JSON.parse(array_json);//object可能是JSONArray，可能是JSONObject
        if (object2 instanceof JSONArray) {//使用instanceof进行判断
            System.out.println("object2 is JSONArray");
            JSONArray array = (JSONArray) object1;//可以使用JSONArray的api进行操作了
        } else {
            //继续使用JSONObject的API操作吧
            System.out.println("object2 is not JSONArray");
        }
    }

}
