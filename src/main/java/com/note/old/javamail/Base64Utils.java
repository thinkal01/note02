package com.note.old.javamail;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class Base64Utils {
    public static String encode(String s) {
        return encode(s, "utf-8");
    }

    public static String decode(String s) {
        return decode(s, "utf-8");
    }

    public static String encode(String s, String charset) {
        try {
            byte[] bytes = s.getBytes(charset);
            bytes = Base64.encodeBase64(bytes);
            return new String(bytes, charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decode(String s, String charset) {
        try {
            byte[] bytes = s.getBytes(charset);
            bytes = Base64.decodeBase64(bytes);
            return new String(bytes, charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test() {
/*      对于标准的Base64：
        加密为字符串使用Base64.getEncoder().encodeToString();
        加密为字节数组使用Base64.getEncoder().encode();
        解密使用Base64.getDecoder().decode();
        对于URL安全或MIME的Base64，只需将上述getEncoder()getDecoder()更换为getUrlEncoder()getUrlDecoder()
        或getMimeEncoder()和getMimeDecoder()即可。*/

        byte[] encode = java.util.Base64.getEncoder().encode("Xue".getBytes());
        String s = new String(encode);
    }
}
