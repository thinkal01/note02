package com.note.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static Map<String, String> cityCodes;

    static {
        cityCodes = new HashMap<>();
        cityCodes.put("11", "北京市");
        cityCodes.put("12", "天津市");
        cityCodes.put("13", "河北省");
        cityCodes.put("14", "山西省");
        cityCodes.put("15", "内蒙古");
        cityCodes.put("21", "辽宁省");
        cityCodes.put("22", "吉林省");
        cityCodes.put("23", "黑龙江省");
        cityCodes.put("31", "上海市");
        cityCodes.put("32", "江苏省");
        cityCodes.put("33", "浙江省");
        cityCodes.put("34", "安徽省");
        cityCodes.put("35", "福建省");
        cityCodes.put("36", "江西省");
        cityCodes.put("37", "山东省");
        cityCodes.put("41", "河南省");
        cityCodes.put("42", "湖北省");
        cityCodes.put("43", "湖南省");
        cityCodes.put("44", "广东省");
        cityCodes.put("45", "广西壮族自治区");
        cityCodes.put("46", "海南省");
        cityCodes.put("50", "重庆市");
        cityCodes.put("51", "四川省");
        cityCodes.put("52", "贵州省");
        cityCodes.put("53", "云南省");
        cityCodes.put("54", "西藏自治区");
        cityCodes.put("61", "陕西省");
        cityCodes.put("62", "甘肃省");
        cityCodes.put("63", "青海省");
        cityCodes.put("64", "宁夏回族自治区");
        cityCodes.put("65", "新疆维吾尔自治区");
        cityCodes.put("71", "台湾省");
        cityCodes.put("81", "香港特别行政区");
        cityCodes.put("82", "澳门特别行政区");
        cityCodes.put("91", "国外");
    }

    public static String getCityCode(String idNumber) {
        String sProvinNum = idNumber.substring(0, 2);
        return cityCodes.get(sProvinNum);
    }

    public static String getSex(String idNumber) {
        String sCardNum = idNumber.substring(16, 17);
        String sGender;
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            sGender = "男";
        } else {
            sGender = "女";
        }

        return sGender;
    }

    /**
     * 正则校验手机号
     *
     * @param mobilephone
     * @return
     */
    public static boolean checkMobilephone(String mobilephone) {
        return mobilephone.matches("^1\\d{10}$");
    }

    /**
     * 正则校验身份证号
     *
     * @param idNumber
     * @return
     */
    public static boolean checkIDNumber(String idNumber) {
        return idNumber.matches("(^\\d{17}(\\d|x|X)$)|(^\\d{15}$)");
    }

    /**
     * 校验信用卡cvn2号
     *
     * @param cvn2
     * @return
     */
    public static boolean checkCvn2(String cvn2) {
        return cvn2.matches("^\\d{3,4}$");
    }

    /**
     * 银行卡加星号处理
     *
     * @return
     */
    public static String addStar2BankNo(String bankNo) {
        if (StringUtils.isEmpty(bankNo)) {
            return "";
        }
        bankNo = bankNo.replaceAll("\\d*(\\d{4})", "**** **** **** $1");
        return bankNo;
    }

    /**
     * 银行卡加星号处理
     *
     * @return
     */
    public static String add4Star2BankNo(String bankNo) {
        if (StringUtils.isEmpty(bankNo)) {
            return "";
        }
        bankNo = bankNo.replaceAll("\\d*(\\d{4})", "**** $1");
        return bankNo;
    }

    /**
     * 身份证加星号处理
     *
     * @param idNumber
     * @return
     */
    public static String addStar2IdNumber(String idNumber) {
        if (StringUtils.isEmpty(idNumber)) {
            return "";
        }
        idNumber = idNumber.replaceAll("(\\d{1})\\d*(\\d{1})", "$1****************$2");
        return idNumber;
    }

    /**
     * 姓名加星号处理
     *
     * @param name
     * @return
     */
    public static String addStar2Name(String name) {
        if (StringUtils.isEmpty(name)) {
            return "";
        }
        if (name.length() == 2) {
            name = "*" + name.charAt(1);
        } else if (name.length() > 2) {
            char begin = name.charAt(0);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < name.length() - 2; ++i) {
                stringBuilder.append('*');
            }
            char end = name.charAt(name.length() - 1);
            name = begin + stringBuilder.toString() + end;
        }

        return name;
    }

    /**
     * 手机号码加星号处理
     *
     * @return
     */
    public static String addStar2Phone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return "";
        }
        phone = phone.replaceAll("(\\d{3})\\d*(\\d{4})", "$1****$2");
        return phone;
    }

    /**
     * 截取四位尾数
     *
     * @param cardNO
     * @return
     */
    public static String intercept4Mantissa(String cardNO) {
        return cardNO.substring(cardNO.length() - 4);
    }

    /**
     * 数组是否包含
     *
     * @param value
     * @param strArr
     * @return
     */
    public static boolean isContainsInArray(String value, String... strArr) {
        for (String str : strArr) {
            if (value.indexOf(str) >= 0) {
                //如果是匿名地址则放行
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串中数字是否全部相同
     *
     * @param num
     * @return
     */
    public static boolean isAllSameNum(Object num) {
        boolean matches = num.toString().matches("^(\\d)\\1+$");
        return matches;
    }

    /**
     * 将字符串数组使用指定的分隔符合并成一个字符串。
     *
     * @param array 字符串数组
     * @param delim 分隔符，为null的时候使用""作为分隔符（即没有分隔符）
     * @return 合并后的字符串
     */
    public static final String combineStringArray(String[] array, String delim) {
        int length = array.length - 1;
        if (delim == null) {
            delim = "";
        }

        StringBuffer result = new StringBuffer(length * 8);
        for (int i = 0; i < length; i++) {
            result.append(array[i]);
            result.append(delim);
        }
        result.append(array[length]);

        return result.toString();
    }

    /**
     * public int indexOf(int ch, int fromIndex)
     * 返回在此字符串中第一次出现指定字符处的索引，从指定的索引开始搜索
     *
     * @param srcText
     * @param findText
     * @return
     */
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        int index = 0;
        while ((index = srcText.indexOf(findText, index)) != -1) {
            index = index + findText.length();
            ++count;
        }
        return count;
    }

    /**
     * 获取指定字符串出现的次数
     *
     * @param srcText  源字符串
     * @param findText 要查找的字符串
     * @return
     */
    public static int appearNumber2(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            ++count;
        }
        return count;
    }

}
