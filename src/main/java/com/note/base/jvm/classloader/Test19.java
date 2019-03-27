package com.note.base.jvm.classloader;

import com.sun.crypto.provider.AESKeyGenerator;

/**
 * Created by zhouyilin on 2018/4/30.
 */
public class Test19 {
    public static void main(String[] args) {
        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();

        System.out.println(aesKeyGenerator.getClass().getClassLoader()); // 该类位于拓展类加载器所加载的目录下s
        System.out.println(Test19.class.getClassLoader());
    }
}
