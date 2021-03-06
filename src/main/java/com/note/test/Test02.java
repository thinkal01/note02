package com.note.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.note.util.CommonUtil;
import org.apache.lucene.store.RateLimiter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;


public class Test02 {
    @Test
    public void test01() {
        new ThreadLocal<Integer>() {
            @Override
            public Integer get() {
                return super.get();
            }
        };
        System.out.println(System.currentTimeMillis());
        System.out.println(CommonUtil.uuid());
    }

    @Test
    public void test02() {
        for (int i = 0; i < 100; ++i) {
            System.out.println(new Random().nextInt(10));
        }
    }

    @Test
    public void test03() {
        // Duration parse = Duration.parse("2000ms");
        // System.out.println(parse);
        runtimeExceptionTest();
    }

    private void runtimeExceptionTest() throws RuntimeException {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}