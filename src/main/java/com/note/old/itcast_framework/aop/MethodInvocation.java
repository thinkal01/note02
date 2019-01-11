package com.note.old.itcast_framework.aop;

import java.lang.reflect.Method;

public class MethodInvocation {
    private Method method;
    private Object target;
    private Object proxy;
    private Object[] args;

    public Object proceed() throws Throwable {
        return method.invoke(target, args);
    }

    public Method getMethod() {
        return method;
    }

    void setMethod(Method method) {
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        return proxy;
    }

    void setProxy(Object proxy) {
        this.proxy = proxy;
    }

    public Object[] getArgs() {
        return args;
    }

    void setArgs(Object[] args) {
        this.args = args;
    }
}
