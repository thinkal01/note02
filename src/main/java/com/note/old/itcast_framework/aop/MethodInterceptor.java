package com.note.old.itcast_framework.aop;

public interface MethodInterceptor extends Advice {
    Object invoke(MethodInvocation mi) throws Throwable;
}
