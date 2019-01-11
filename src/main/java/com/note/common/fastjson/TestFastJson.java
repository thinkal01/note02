package com.note.common.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class TestFastJson {

    public static void main(String[] args) {
        String jsonString = JSON.toJSONString(new Test("a/b/c/d", "b"));
        System.out.println(jsonString);
    }

    static class Test {
        @JSONField(name = "a")
        private String str1;

        @JSONField(name = "b")
        private String str2;

        public String getStr1() {
            return str1;
        }

        public void setStr1(String str1) {
            this.str1 = str1;
        }

        public String getStr2() {
            return str2;
        }

        public void setStr2(String str2) {
            this.str2 = str2;
        }

        public Test(String str1, String str2) {
            super();
            this.str1 = str1;
            this.str2 = str2;
        }

    }
}
