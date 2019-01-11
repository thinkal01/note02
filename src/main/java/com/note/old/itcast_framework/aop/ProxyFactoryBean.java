package com.note.old.itcast_framework.aop;

import com.note.old.itcast_framework.beanfactory.factorybean.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactoryBean implements FactoryBean {
    private Advice advice;
    private Object target;


    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getObject() {
        ClassLoader l = Thread.currentThread().getContextClassLoader();
        Class[] interfaces = target.getClass().getInterfaces();
        InvocationHandler ih = null;

        if (advice instanceof BeforeAdvice) {
            ih = this.getBeforeHandler();
        } else if (advice instanceof AfterAdvice) {
            ih = this.getAfterHandler();
        } else if (advice instanceof MethodInterceptor) {
            ih = this.getMethodHandler();
        }

        return Proxy.newProxyInstance(l, interfaces, ih);
    }

    private InvocationHandler getAfterHandler() {
        final AfterAdvice afterAdvice = (AfterAdvice) advice;
        InvocationHandler ih = (proxy, method, args) -> {
            Object returnValue = method.invoke(target, args);
            afterAdvice.after(returnValue, method, args, target);
            return returnValue;
        };
        return ih;
    }

    private InvocationHandler getBeforeHandler() {
        final BeforeAdvice beforeAdvice = (BeforeAdvice) advice;
        InvocationHandler ih = (proxy, method, args) -> {
            beforeAdvice.before(method, args, target);
            return method.invoke(target, args);
        };
        return ih;
    }

    private InvocationHandler getMethodHandler() {
        final MethodInterceptor interceptor = (MethodInterceptor) advice;
        InvocationHandler ih = (proxy, method, args) -> {
            MethodInvocation mi = new MethodInvocation();
            mi.setArgs(args);
            mi.setMethod(method);
            mi.setProxy(proxy);
            mi.setTarget(target);
            return interceptor.invoke(mi);
        };
        return ih;
    }
}
