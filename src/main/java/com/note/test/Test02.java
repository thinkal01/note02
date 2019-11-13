package com.note.test;

import com.note.util.CommonUtil;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.spi.inject.Inject;
import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import org.apache.poi.util.StringUtil;
import org.hibernate.engine.spi.FilterDefinition;
import org.hyperic.sigar.cmd.Route;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.main.server.HeartbeatThread;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

