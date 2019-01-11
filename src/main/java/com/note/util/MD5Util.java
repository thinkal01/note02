package com.note.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;

public class MD5Util {

    private static Log log = LogFactory.getLog(MD5Util.class);

    // 全局数组
    private final static String[] strDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public MD5Util() {
    }

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }

        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }

        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String GetMD5Code(String strObj) {
        String resultString = null;

        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return resultString;
    }

    /**
     * 签名生成算法
     *
     * @param params<String,String> params 请求参数集，所有参数必须已转换为字符串类型
     * @param secret                secret 签名密钥
     * @return 签名
     * @throws IOException
     */
    public static String getSignature(Map<String, String> params, String secret) throws IOException {
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        Set<Entry<String, String>> entrys = sortedParams.entrySet();

        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Entry<String, String> param : entrys) {
            basestring.append(param.getKey()).append("=").append(param.getValue());
        }
        basestring.append(secret);
        log.info(basestring);
        // 使用MD5对待签名串求签
        byte[] bytes = null;

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
        } catch (GeneralSecurityException ex) {
            throw new IOException(ex);
        }

        // 将MD5输出的二进制结果转换为小写的十六进制
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }

        return sign.toString();
    }

    /**
     * 签名生成算法
     *
     * @param lists  <NameValuePair> lists 请求参数集，所有参数必须已转换为字符串类型
     * @param secret secret 签名密钥
     * @return 签名
     * @throws IOException
     */
    public static String getSignature(List<BasicNameValuePair> lists, String secret) {
        try {
            HashMap<String, String> params = new HashMap<String, String>();
            for (BasicNameValuePair nameValuePair : lists) {
                params.put(nameValuePair.getName(), nameValuePair.getValue());
            }

            if (params.size() == 0) {
                return null;
            } else
                return getSignature(params, secret);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 验证返回参数
     *
     * @param params 返回参数
     * @param secret 密钥
     * @return
     */
    public static boolean checkParam(Map<String, String> params, String secret) {
        boolean result = false;
        if (params == null || params.size() == 0) {
            result = true;
            return result;
        }

        if (params.containsKey("sign")) {
            String sign = params.get("sign");
            params.remove("sign");
            String signRecieve = null;
            try {
                signRecieve = getSignature(params, secret);
            } catch (IOException e) {
                e.printStackTrace();
            }
            result = sign.equalsIgnoreCase(signRecieve);
        }

        return result;
    }

}
