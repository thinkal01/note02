package com.note.old.itcast_framework.aop;

import java.lang.reflect.Method;

public interface AfterAdvice extends Advice {
    void after(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}
