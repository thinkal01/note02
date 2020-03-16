package com.note.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class String02 {
    @Test
    public void test() {
        String str = "----------------------------095164280514254942896208\n" +
                "Content-Disposition: form-data; name=\"ani\"\n" +
                "\n" +
                "923847589\n" +
                "----------------------------095164280514254942896208\n" +
                "Content-Disposition: form-data; name=\"asdf\"\n" +
                "\n" +
                "asdf\n" +
                "----------------------------095164280514254942896208--\n";
        String[] split = str.split("\n");
        for (int i = 0; i < split.length - 1; ++i) {
            System.out.println(MessageFormat.format("{0},{1}", split[++i], split[i = i + 2]));
        }

        String hashKey = "phone";
        // .不包含\r,\n
        String regex = "name=\"" + hashKey + "\"\n\n(.+)";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(str);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    @Test
    public void test02() {
        String body = "{\n" +
                "    \"head\": {\n" +
                "        \"ani\": \"2132134\"\n" +
                "    },\n" +
                "    \"body\": {\n" +
                "        \"requestCode\": \"4\"\n" +
                "    }\n" +
                "}";
        boolean b = body.startsWith("{");
        System.out.println("是否为json:" + b);
        JSONObject jsonObject = JSON.parseObject(body);
        JSONObject head = jsonObject.getJSONObject("head");
        String ani = head.getString("ani");
        System.out.println(ani);
    }

    @Test
    public void test03() {
        String body = "{\n" +
                "    \"head\": {\n" +
                "        \"ani\": \"2132134\"\n" +
                "    },\n" +
                "    \"body\": {\n" +
                "        \"requestCode\": \"4\"\n" +
                "    }\n" +
                "}";
        String body1 = "----------------------------095164280514254942896208\n" +
                "Content-Disposition: form-data; name=\"ani\"\n" +
                "\n" +
                "923847589\n" +
                "----------------------------095164280514254942896208\n" +
                "Content-Disposition: form-data; name=\"asdf\"\n" +
                "\n" +
                "asdf\n" +
                "----------------------------095164280514254942896208--\n";
        String hashKey = "ani";
        String regex = "\"" + hashKey + "\"\\s*:?\\s*(\r\n|\n)*\"?(.+)\"?";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(body1);
        if (matcher.find()) {
            System.out.println(matcher.group(2));
        }
    }
}