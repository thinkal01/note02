package com.note.util;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;

public class HashUtil {

    // jdk所有算法
    @Test
    public void test() {
        Provider[] providers = Security.getProviders();
        for (Provider p : providers) {
            System.out.println("provider name:" + p.getName());
            for (Provider.Service s : p.getServices()) {
                System.out.println("类型:" + s.getType() + "，算法：" + s.getAlgorithm());
            }
            System.out.println("---------------------------------------------------------");
        }
    }

    /**
     * 利用java原生的类实现SHA256加密
     *
     * @param str 原文
     * @return 加密后的报文
     */
    public static String getSHA256(String str) {
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    /**
     * 使用SHA-256进行迭代加密
     * MessageDigest，支持算法：MD2,MD5,SHA,SHA-224,SHA-256,SHA-384,SHA-512
     *
     * @param source         加密字符串
     * @param salt           盐
     * @param hashIterations 迭代次数
     * @return 加密字符串
     */
    public static String getHashStr(String algorithmName, String source, String salt, int hashIterations) {
        MessageDigest digest;
        String encodeStr = "";
        try {
            digest = MessageDigest.getInstance("SHA-256");
            if (salt != null) {
                digest.reset();
                digest.update(salt.getBytes("UTF-8"));
            }

            byte[] hashed = digest.digest(source.getBytes("UTF-8"));
            int iterations = hashIterations - 1;

            for (int i = 0; i < iterations; ++i) {
                digest.reset();
                hashed = digest.digest(hashed);
            }
            encodeStr = byte2Hex(hashed);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encodeStr;
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) throws Exception {
        // String sha256 = getSHA256("YzcmCZNvbXocrsz9dm8e" + "admin");
        String sha256 = getHashStr("SHA-256", "admin", "YzcmCZNvbXocrsz9dm8e", 16);
        System.out.println(sha256);
    }
}
