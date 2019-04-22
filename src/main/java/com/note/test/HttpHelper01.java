package com.note.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class HttpHelper01 {

    /*
    示例
     */
    @Test
    public void test() throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", "1");
        // 机构号
        map.put("appId", "01");
        String s = doPost("http://192.168.100.168:8090/detail", map);
        System.out.println(s);
    }

    public static String doPost(String url, Map<String, Object> parameters) throws IOException {
        byte[] entity;
        String s = JSON.toJSONString(parameters);
        String encrypt = RSAUtil.encryptByPublicKey(s);
        System.out.println(encrypt);
        encrypt = URLEncoder.encode(encrypt, "UTF-8");
        System.out.println(encrypt);
        entity = post(url, "signData=" + encrypt);
        return new String(entity);
    }

    private static byte[] post(String url, String parameter) throws IOException {
        byte[] result = null;
        HttpURLConnection conn;
        conn = getHttpURLConnection(url);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        PrintWriter out = null;

        try {
            if (parameter != null && !parameter.equals("")) {
                out = new PrintWriter(conn.getOutputStream());
                out.write(parameter);
                out.flush();
            }

            if (conn.getResponseCode() == 200) {
                result = getResponseData(conn);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
            conn.disconnect();
        }

        return result;
    }

    public static HttpURLConnection getHttpURLConnection(String url) throws IOException {
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Connection", "close");
        return conn;
    }

    public static byte[] getResponseData(HttpURLConnection conn) throws IOException {
        ByteArrayOutputStream out = null;
        BufferedInputStream bis = null;
        InputStream in = null;

        try {
            in = conn.getInputStream();
            bis = new BufferedInputStream(in);
            byte[] buf = new byte[1024];
            int len;
            out = new ByteArrayOutputStream();

            while ((len = bis.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            closeStream(in);
            closeStream(bis);
            closeStream(out);
        }

        return out.toByteArray();
    }

    public static void closeStream(Closeable in) {
        if (in != null) {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * RSA加解密工具类
     */
    static class RSAUtil {
        public static String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKB+4TKXFPSw6/NiII+N108z9XlxrxCP/w5creK5P6eNTJ2hfNfmB49UvUL4DC3fYVJGvnvmu3oWuxQ2DdJ1Uq8CAwEAAQ=="; // 公钥

        /**
         * 公钥匙加密或解密
         */
        public static String encryptByPublicKey(String content) {
            return encryptByPublicKey(content, PUBLIC_KEY, Cipher.ENCRYPT_MODE);
        }

        public static String encryptByPublicKey(String content, String publicKeyStr, int opmode) {
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

    }

}