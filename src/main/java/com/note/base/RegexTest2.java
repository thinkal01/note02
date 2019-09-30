package com.note.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest2 {
    // 爬取邮箱地址
    public static List<String> getMailsByWeb() throws IOException {
        // 网络文件
        URL url = new URL("http://192.168.1.100:8080/myweb/mail.html");
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(url.openStream()));
        // 本地文件
        // BufferedReader bufr = new BufferedReader(new FileReader("c:\\mail.html"));

        // 对读取的数据进行规则的匹配。从中获取符合规则的数据
        String mail_regex = "\\w+@\\w+(\\.\\w+)+";
        List<String> list = new ArrayList<>();
        Pattern p = Pattern.compile(mail_regex);
        String line;

        while ((line = bufIn.readLine()) != null) {
            Matcher m = p.matcher(line);
            while (m.find()) {
                //3,将符合规则的数据存储到集合中。
                list.add(m.group());
            }
        }

        return list;
    }
}