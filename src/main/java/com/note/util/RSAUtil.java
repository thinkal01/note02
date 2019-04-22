package com.note.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

/**
 * RSA加解密工具类
 */
public class RSAUtil {
    public static String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKB+4TKXFPSw6/NiII+N108z9XlxrxCP/w5creK5P6eNTJ2hfNfmB49UvUL4DC3fYVJGvnvmu3oWuxQ2DdJ1Uq8CAwEAAQ=="; // 公钥
    public static String PRIVATE_KEY = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAoH7hMpcU9LDr82Igj43XTzP1eXGvEI//Dlyt4rk/p41MnaF81+YHj1S9QvgMLd9hUka+e+a7eha7FDYN0nVSrwIDAQABAkAngjRjpMd22NqspjpHvG4FRFAoXjDvTdNGsM/pYRrnXsBt+P8232BSWhOmLPs9Wm2LHMrfucx8ttmA611k0zsJAiEAzpIn42d8jTPDhvSBSpw/IlNvFl6deOE8+jiuzquM6VUCIQDG5k4eKXTWIJA2Yb/eplbPZwbpf6RO0r946k24STh78wIhAMjOU3BAPVeWJlCehsDcupLHwIliWHoVmo0zZNsK8OQxAiAOKfpoJXWfhgQfC0j5lwjZjjGs0R0nS9S8zJqqQ/SUxwIgBoLDXcbWa6CQLmw5hWM5xHvsU1oh4qul7Fhi4UCcxPE="; // 私钥

    /**
     * 生成公钥和私钥
     */
    public static void generateKey() {
        // 1.初始化秘钥
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom sr = new SecureRandom(); // 随机数生成器
            keyPairGenerator.initialize(512, sr); // 设置512位长的秘钥
            KeyPair keyPair = keyPairGenerator.generateKeyPair(); // 开始创建
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 进行转码
            PUBLIC_KEY = Base64.encodeBase64String(rsaPublicKey.getEncoded());
            // 进行转码
            PRIVATE_KEY = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // 私钥解密
    public static String decryptByPrivateKey(String content) {
        return encryptByPrivateKey(content, PRIVATE_KEY, Cipher.DECRYPT_MODE);
    }

    /**
     * 私钥匙加密或解密
     */
    public static String encryptByPrivateKey(String content, String privateKeyStr, int opmode) {
        // 私钥要用PKCS8进行处理
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr));
        KeyFactory keyFactory;
        PrivateKey privateKey;
        Cipher cipher;
        byte[] result;
        String text = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            // 还原Key对象
            privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(opmode, privateKey);
            if (opmode == Cipher.ENCRYPT_MODE) { // 加密
                result = cipher.doFinal(content.getBytes());
                text = Base64.encodeBase64String(result);
            } else if (opmode == Cipher.DECRYPT_MODE) { // 解密
                result = cipher.doFinal(Base64.decodeBase64(content));
                text = new String(result, "UTF-8");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 公钥解密
     */
    public static String encryptByPublicKey(String content) {
        return decryptByPublicKey(content, PUBLIC_KEY, Cipher.ENCRYPT_MODE);
    }

    /**
     * 公钥匙加密或解密
     */
    public static String decryptByPublicKey(String content, String publicKeyStr, int opmode) {
        // 公钥要用X509进行处理
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
        KeyFactory keyFactory;
        PublicKey publicKey;
        Cipher cipher;
        byte[] result;
        String text = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            // 还原Key对象
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(opmode, publicKey);
            if (opmode == Cipher.ENCRYPT_MODE) { // 加密
                result = cipher.doFinal(content.getBytes());
                text = Base64.encodeBase64String(result);
            } else if (opmode == Cipher.DECRYPT_MODE) { // 解密
                result = cipher.doFinal(Base64.decodeBase64(content));
                text = new String(result, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static void main(String[] args) {
        /**
         * 注意： 私钥加密必须公钥解密 公钥加密必须私钥解密
         */
        System.out.println("-------------生成两对秘钥，分别发送方和接收方保管-------------");
        // RSAUtil.generateKey();
        System.out.println("公钥匙给接收方:" + RSAUtil.PUBLIC_KEY);
        System.out.println("私钥给发送方:" + RSAUtil.PRIVATE_KEY);

        System.out.println("-------------第一个栗子，私钥加密公钥解密-------------");
        String textsr = "早啊，你吃早饭了吗？O(∩_∩)O~";
        // 私钥加密
        String cipherText = RSAUtil.encryptByPrivateKey(textsr, RSAUtil.PRIVATE_KEY, Cipher.ENCRYPT_MODE);
        System.out.println("发送方用私钥加密后：" + cipherText);
        // 公钥解密
        String text = RSAUtil.decryptByPublicKey(cipherText, RSAUtil.PUBLIC_KEY, Cipher.DECRYPT_MODE);
        System.out.println("接收方用公钥解密后：" + text);

        System.out.println("-------------第二个栗子，公钥加密私钥解密-------------");
        // 公钥加密
        cipherText = RSAUtil.decryptByPublicKey(textsr, RSAUtil.PUBLIC_KEY, Cipher.ENCRYPT_MODE);
        System.out.println("接收方用公钥加密后：" + cipherText);
        // 私钥解密
        text = RSAUtil.encryptByPrivateKey(cipherText, RSAUtil.PRIVATE_KEY, Cipher.DECRYPT_MODE);
        System.out.print("发送方用私钥解密后：" + text);
    }

    @Test
    public void test() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", "1");
        // 机构号
        map.put("appId", "01");
        String s = JSON.toJSONString(map);
        String encrypt = encryptByPublicKey(s);
        encrypt = "WXWBGiH3tooEou/0mp5gT89vSWjF5DqRlAl/nxUvEpguX+XtrgY2yH5dv2dawyONZkZxWiX0RJD4j6sghq7eeg==";
        System.out.println(encrypt);
        s = RSAUtil.decryptByPrivateKey(encrypt);
        System.out.println(s);
    }

}