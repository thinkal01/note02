package com.note.base.thread.heima;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocal02 {

    @Test
    public void test01() {
        ThreadLocal<Map<Object, Object>> hashMapThreadLocal = new ThreadLocal<Map<Object, Object>>() {
            // 重写get()方法,实现懒加载
            @Override
            public Map<Object, Object> get() {
                Map<Object, Object> hashMap = super.get();
                return hashMap != null ? hashMap : new HashMap<>();
            }
        };

        // get()时再进行初始化操作
        // ThreadLocal<HashMap<Object, Object>> hashMapThreadLocal = ThreadLocal.withInitial(HashMap::new);
        hashMapThreadLocal.get().put("a", "b");
        hashMapThreadLocal.remove();
        // 会重新初始化
        System.out.println(hashMapThreadLocal.get());
    }
}