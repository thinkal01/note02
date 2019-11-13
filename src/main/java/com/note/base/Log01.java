package com.note.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class Log01 {
    @Test
    public void test() {
        String s = null;
        // null
        log.info("测试null日志,结果为{}", s);
    }
}
