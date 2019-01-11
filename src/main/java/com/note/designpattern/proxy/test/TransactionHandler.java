package com.note.designpattern.proxy.test;

import com.note.designpattern.proxy.InvocationHandler;

import java.lang.reflect.Method;

public class TransactionHandler implements InvocationHandler {

    private Object target;

    public TransactionHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public void invoke(Object o, Method m) {
        System.out.println("Transaction Start");
        try {
            m.invoke(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Transaction Commit");
    }

}
