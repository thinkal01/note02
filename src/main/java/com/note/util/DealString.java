package com.note.util;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DealString {

    /**
     * 数字的金额表达式
     *
     * @param num
     * @return
     */
    public static String convertNumToMoney(int num) {
        NumberFormat formatc = NumberFormat.getCurrencyInstance(Locale.CHINA);
        String strcurr = formatc.format(num);
        //num = NumberFormat.getInstance().setParseIntegerOnly(true));
        return strcurr;
    }

    /**
     * 压缩XML字符串，去除无用的空格/tab/换行符
     * 1、">"和"<"之间的所有空白字符全部去除;
     * 2、"<"和">"之间的所有相邻空白仅保留一个;
     */
    public static String compactXml(String s) {
        StringBuffer ret = new StringBuffer();
        boolean valid = false;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isWhitespace(ch)) {
                if (!valid)
                    continue;
                    // <>内部多个空白符,处理为一个空白符
                else if (i > 1 && Character.isWhitespace(s.charAt(i - 1)))
                    continue;
            }
            if (ch == '<') valid = true;
            if (ch == '>') valid = false;
            ret.append(ch);
        }

        return ret.toString();
    }

    /**
     * 判断对象是否为空或仅包含空白字符
     *
     * @param s
     * @return
     */
    public static boolean isBlank(Object s) {
        return isEmptyOrBlank(s, true);
    }

    /**
     * 判断给定对象s是否为空
     * 如果s为null，返回真
     * 如果s是0长字符串，返回真
     * 如果s是字符串数组，且数组个数为0，返回真
     * 如果s是字符串数组，且数组中的每个元素都是空字符串，返回真
     * 如果s是集合，集合/元素为空,返回真
     * 其它情况返回假
     */
    public static boolean isEmpty(Object s) {
        return isEmptyOrBlank(s, false);
    }

    private static boolean isEmptyOrBlank(Object s, boolean trim) {
        if (s == null) return true;
        if (s instanceof String) {
            String ss = (String) s;
            return (trim ? ss.trim() : ss).length() == 0;
        }
        if (s instanceof Object[]) {
            for (Object o : (Object[]) s)
                if (!isEmptyOrBlank(o, trim)) return false;
            return true;
        }
        if (s instanceof Collection) {
            for (Object o : (Collection<?>) s)
                if (!isEmptyOrBlank(o, trim)) return false;
            return true;
        }
        return false;
    }

    public static String nullable(Object s) {
        return s == null ? "" : s.toString();
    }

    /**
     * 去掉尾部suffix子串
     *
     * @param s
     * @param suffix
     * @return
     */
    public static String rtrim(String s, String suffix) {
        if (s.endsWith(suffix)) return s.substring(0, s.length() - suffix.length());
        return s;
    }

    /**
     * 去掉尾部ch字符
     *
     * @param str
     * @param ch
     * @return
     */
    public static String unPadding(String str, char ch) {
        while (str.charAt(str.length() - 1) == ch) str = str.substring(0, str.length() - 1);
        return str;
    }

    /**
     * 首字母大写
     *
     * @param s
     * @return
     */
    public static String capitalFirst(String s) {
        return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s
     * @return
     */
    public static String uncapitalFirst(String s) {
        return String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1);
    }

    /**
     * 右边空格补全
     *
     * @param s
     * @param n 补全后总长度
     * @return
     */
    public static String rspace(String s, int n) {
        StringBuffer sb = new StringBuffer();
        sb.append(s);
        for (int i = 0; i < n - s.length(); i++)
            sb.append(" ");
        return sb.toString();
    }

    /**
     * 左边padding补全
     *
     * @param s
     * @param n       补全后总长度
     * @param padding
     * @return
     */
    public static String lpadding(String s, int n, String padding) {
        StringBuffer strbuf = new StringBuffer();
        for (int i = 0; i < n - s.length(); i++) {
            strbuf.append(padding);
        }
        strbuf.append(s);
        return strbuf.toString();
    }

    /**
     * 生成n个空格
     *
     * @param n
     * @return
     */
    public static String space(int n) {
        String ret = "";
//        for (int i = 0; i < n; i++) ret += " ";
        ret = replicateStr(' ', n);
        return ret;
    }


    /**
     * 取以"."分隔字符串中的前部，若字符串没有包含字符"."，则返回""
     */
    public static String getFirstByDot(String str) {
        int index = str.indexOf(".");
        if (index == -1) return "";
        return str.substring(0, index);
    }

    /**
     * 取以"."分隔字符串中的后部，若字符串没有包含字符"."，则返回""
     */
    public static String getSecondByDot(String str) {
        int index = str.indexOf(".");
        if (index == -1) return str;
        return str.substring(index + 1, str.length());
    }

    /**
     * 将字符串s按空格和逗号拆分为列表
     * 与String.split方法不同的地方是：本方法的返回结果中不包括零长字符串
     */
    public static List<String> split(String s) {
        return split(s, "[\\s,]+");
    }

    /**
     * 与String.split方法不同的地方是：本方法的返回结果中不包括零长字符串
     */
    public static List<String> split(String input, String sep) {
        if (input == null) return null;
        int index = 0;
        List<String> matchList = new ArrayList<>();
        Matcher m = Pattern.compile(sep).matcher(input);

        while (m.find()) {
            // 去掉长度为0的字符串
            if (index < m.start()) {
                String match = (String) input.subSequence(index, m.start());
                matchList.add(match);
            }
            index = m.end();
        }

        if (index < input.length())
            matchList.add(input.subSequence(index, input.length()).toString());

        return matchList;
    }

    /**
     * 得到类似str1,str2,str3的值
     *
     * @param limit
     * @param args
     * @return
     */
    public static String join(String limit, String... args) {
        if (args == null) return "";
        return join(limit, Arrays.asList(args));
    }

    /**
     * 得到类似str1,str2,str3的值
     *
     * @param limit
     * @param args
     * @return
     */
    public static String join(String limit, List<String> args) {
        if (args == null || args.size() == 0) return "";
        if (args.size() == 1) return args.get(0);

        StringBuffer ret = new StringBuffer();
        ret.append(args.get(0));

        for (int i = 1; i < args.size(); i++)
            ret.append(limit).append(args.get(i));

        return ret.toString();
    }

    /**
     * 转字符串数组
     *
     * @param array
     * @return
     */
    public static String[] objArray2StrArray(Object[] array) {
        String[] ret = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            ret[i] = array[i].toString();
        }

        return ret;
    }

    /**
     * 产生重复字符串
     */
    public static String replicateStr(char ch, int len) {
        String tmpstr = null;
        char[] tmparr = null;

        if (len <= 0) {
            return "";
        }

        tmparr = new char[len];
        for (int i = 0; i < len; i++) {
            tmparr[i] = ch;
        }
        tmpstr = new String(tmparr);

        return tmpstr;
    }


    /**
     * 取带汉字字串的length 将带字符串按Byte位长度取子字符串 防止带汉字的字符串长度取错
     *
     * @param str 源字符串
     */
    public static int srcStrLen(String str, String charSet) {
        if (str == null) {
            return 0;
        }

        byte[] strByte;
        try {
            strByte = str.getBytes(charSet);
        } catch (UnsupportedEncodingException e) {
            strByte = str.getBytes();
        }

        return strByte.length;
    }

}
