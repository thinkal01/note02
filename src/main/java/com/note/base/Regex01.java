package com.note.base;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex01 {
    @Test
    public void regexTest() {
        String regex = "[1-9]\\d{4,14}";
        // email
        regex = "\\w+@\\w{2,6}(\\.\\w{2,3})+";

        String s2 = "aa.bb.cc";
        String[] str2Array = s2.split("\\.");

        String s3 = "aa    bb                cc";
        String[] str3Array = s3.split(" +");

        //硬盘上的路径，我们应该用\\替代\
        String s4 = "E:\\JavaSE\\day14\\avi";
        regex = "\\\\";
        String[] str4Array = s4.split(regex);

        // 定义一个字符串
        String s = "helloqq12345worldkh622112345678java";
        // 去除所有的数字
        regex = "\\d+";
        String ss = "";
        String result = s.replaceAll(regex, ss);
    }

    public void testMatcher() {
        String s = "da jia ting wo shuo,jin tian yao xia yu,bu shang wan zi xi,gao xing bu?";
        String regex = "\\b\\w{3}\\b";
        Pattern p = Pattern.compile(regex);
        // 通过模式对象得到匹配器对象
        Matcher m = p.matcher(s);

        // 注意：一定要先find()，然后才能group()
        // IllegalStateException: No match found
        while (m.find()) {
            System.out.println(m.group());
        }
    }


    @Test
    public void split01() {
        String s = "84944135311K01N||1537|30||4900|19||||134941071398977233||";
        String[] split = s.split("\\|");

        String a = "a";
        split = a.split(",");
    }

    // 通过路径获取文件名
    @Test
    public void test02() {
        String regExp = ".+\\\\(.+)$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher("e:\\a.txt");
        // true
        boolean b = m.find();
        // a.txt
        String group = m.group(1);
    }

    @Test
    public void test03() {
        String regex = "(present\\*|time\\*)([<>=]+\\d)";
        String line = "*present*==3&&*time*>=4";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        System.out.println(m.matches());
        while (m.find()) {
            System.out.println(m.group());
            // System.out.println(m.group(0)); // 所有内容
            // System.out.println(m.group(1)); // 第一个括号
            System.out.println(m.group(2)); // 第二个括号
        }
    }
}
