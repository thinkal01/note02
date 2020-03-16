package com.note.util;

import org.junit.Test;

public class StarUtil {

    public static boolean isLike(String source, String regex) {
        return isLike(source, regex, false);
    }

    /**
     * 判断source字符串是否能够被regex匹配,源字符串不能包含?,*
     * @param source 任意字符串
     * @param regex 包含*或?的匹配表达式
     * @param ignoreCase 大小写敏感
     */
    public static boolean isLike(String source, String regex, boolean ignoreCase) {
        if (source == null || regex == null) return false;
        if (ignoreCase) {
            source = source.toLowerCase();
            regex = regex.toLowerCase();
        }
        //去除多余*号
        return matches(source, regex.replaceAll("\\*{2,}", "*"));
    }

    private static boolean matches(String source, String regex) {
        // 如果source与regex完全相等，返回true
        if (source.equals(regex)) return true;
        int regexIdx = 0, sourceIdx = 0;
        while (regexIdx < regex.length() && sourceIdx < source.length()) {
            //以匹配表达式为主导
            char c = regex.charAt(regexIdx);
            switch (c) {
                case '*':
                    //从*后一位开始是新的匹配表达式,到字符串长度时为""
                    String tempRegex = regex.substring(regexIdx + 1);
                    //此处等号不能缺，如（ABCD，*），等号能达成("", *)条件
                    for (int j = sourceIdx; j <= source.length(); j++) {
                        //去除前面已经完全匹配的前缀
                        String tempSource = source.substring(j);
                        if (matches(tempSource, tempRegex)) {
                            return true;
                        }
                    }
                    return false;
                case '?':
                    break;
                default:
                    //普通字符匹配
                    if (source.charAt(sourceIdx) != c) return false;
            }
            regexIdx++;
            sourceIdx++;
        }

        //最终source被匹配完全，而regex也被匹配完整或只剩一个*号
        return source.length() == sourceIdx &&
                (regex.length() == regexIdx || regex.length() == regexIdx + 1 && regex.charAt(regexIdx) == '*');
    }

    @Test
    public void main() {
        System.out.println("str=ABCD regex=ABC? :" + isLike("ABCD", "ABC?"));
        System.out.println("str=ABCD regex=A??? :" + isLike("ABCD", "A???"));
        System.out.println("str=ABCD regex=?BC? :" + isLike("ABCD", "?BC?"));
        System.out.println("str=ABCD regex=A?? :" + isLike("ABCD", "A??"));
        System.out.println("str=ABCD regex=A* :" + isLike("ABCD", "A*"));
        System.out.println("str=ABCD regex=*BCD :" + isLike("ABCD", "*BCD"));
        System.out.println("str=ABCD regex=*B*D :" + isLike("ABCD", "*B*D"));
        System.out.println("str=ABCD regex=*AB*CD* :" + isLike("ABCD", "*AB*CD*"));
    }
}