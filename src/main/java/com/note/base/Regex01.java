package com.note.base;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Regex01 {
    @Test
    public void replaceAll01() {
        String str = "zhangsanttttxiaoqiangmmmmmmzhaoliu";
        // 去除字符串重复字符
        str = str.replaceAll("(.)\\1+", "$1");
        String tel = "15800001111";//158****1111;
        // 电话添加*号
        tel = tel.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");

        String s = "helloqq12345worldkh622112345678java";
        // 去除所有的数字
        String result = s.replaceAll("\\d+", "");
    }

    @Test
    public void split01() {
        String str = "zhangsanttttxiaoqiangmmmmmmzhaoliu";
        String[] names = str.split("(.)\\1+");
        // String[] names = str.split("\\.");
        Stream.of(names).forEach(System.out::println); //
    }

    @Test
    public void split02() {
        String s = "84944135311K01N||1537|30||4900|19||||134941071398977233||";
        String[] split = s.split("\\|");

        String a = "a";
        split = a.split(","); // ["a"]

        String s2 = "aa.bb.cc";
        String[] str2Array = s2.split("\\.");

        String s3 = "aa    bb                cc";
        String[] str3Array = s3.split(" +");

        //硬盘上的路径，应该用\\替代\
        String s4 = "E:\\JavaSE\\day14\\avi";
        String[] str4Array = s4.split("\\\\");
    }

    @Test
    public void matches01() {
        //匹配手机号码是否正确
        String tel = "15800001111";
        String regex = "1[358]\\d{9}";
        boolean b = tel.matches(regex);
    }

    @Test
    public void regexTest() {
        // 邮件地址校验
        String regex = "\\w+@\\w{2,6}(\\.\\w{2,3})+";
        regex = "\\w+@\\w+(\\.\\w+)+";//1@1.1

        String mail = "abc1@sina.com.cn";
        boolean b = mail.matches(regex);
    }

    // ip地址排序
    public static void test_2() {
        String ip_str = "192.168.10.34 127.0.0.1 3.3.3.3 105.70.11.55";
        //为了让ip可以按照字符串顺序比较，要让ip的每一段的位数相同。
        //每一段都加两个0
        ip_str = ip_str.replaceAll("(\\d+)", "00$1");
        //然后每一段保留数字3位。
        ip_str = ip_str.replaceAll("0*(\\d{3})", "$1");

        //将ip地址切出。
        String[] ips = ip_str.split(" +");
        TreeSet<String> ts = new TreeSet<>();
        ts.addAll(Arrays.asList(ips));
        for (String ip : ts) {
            // 去掉前面多余的0
            System.out.println(ip.replaceAll("0*(\\d+)", "$1"));
        }
    }

    public void testMatcher() {
        String s = "da jia ting wo shuo,jin tian yao xia yu,bu shang wan zi xi,gao xing bu?";
        // 单词边界
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


    // 通过路径获取文件名
    @Test
    public void test02() {
        String regExp = ".+\\\\(.+)$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher("e:\\a.txt");
        // a.txt
        String group = m.group(1);
    }

    @Test
    public void test04() {
        String regex = "\\(*(\\*(present|time)\\*)([<>=]+\\d)\\)*";
        // pattern可以被重复使用
        Pattern p = Pattern.compile(regex);

        String line = "(*present*==3)&&*time*>=4";
        Matcher m = p.matcher(line);
        while (m.find()) {
            System.out.println(m.start()); // 匹配到的字符串开始
            System.out.println(m.end()); // 匹配到的字符串结束
            System.out.println(line.substring(m.start(), m.end())); // 匹配到的子串
            System.out.println(m.regionStart()); // 字符串开始
            System.out.println(m.regionEnd()); // 字符串结束
            System.out.println(line.substring(m.regionStart(), m.regionEnd())); // 字符串全部

            System.out.println(m.group()); // 匹配到的所有内容
            System.out.println(m.group(0)); // 匹配到的所有内容
            System.out.println(m.group(1)); // 第一个括号
            System.out.println(m.group(2)); // 第二个括号
            System.out.println(m.group(3)); // 第三个括号
        }
    }

    @Test
    public void test05() {
        // System.out.println("A**\\B**C*".replaceAll("(^|([^\\\\]))[\\*]{2,}", "$2*"));
        System.out.println("******".replaceAll("(^|(^\\\\))\\*{2,}", "$2*"));
        System.out.println("A**\\\\B**C*".replaceAll("(^|([^\\\\]))\\*{2,}", "$2*"));
        System.out.println("******".replaceAll("(^|(^\\\\))\\*{2,}", "$2*"));
        System.out.println("A**\\\\B**C*".replaceAll("\\*{2,}", "*"));
    }
}