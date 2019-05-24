package com.note.common.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class FastJson01 {

    @Test
    public void test01() {
        // json为null或"",不会报错
        JSONObject jsonObject = JSON.parseObject("");
        jsonObject = JSON.parseObject("{}");
        jsonObject = JSON.parseObject(null);
        return;
    }
}
