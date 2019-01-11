package com.note.old.jdbc.itcast.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Demo {
    @Test
    public void fun1() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class[] interfaces = {Waiter.class};
        Waiter watier = new WaiterImpl();
        InvocationHandler h = new WaiterHandler(watier);
        Waiter proxy = (Waiter) Proxy.newProxyInstance(loader, interfaces, h);
        proxy.serve();
    }
}
