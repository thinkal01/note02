package com.note.designpattern.proxy.test;

import com.note.designpattern.proxy.InvocationHandler;
import com.note.designpattern.proxy.Proxy;

public class Client {
    public static void main(String[] args) throws Exception {
        UserMgr mgr = new UserMgrImpl();
        InvocationHandler h = new TransactionHandler(mgr);
        //TimeHandler h2 = new TimeHandler(h);
        UserMgr u = (UserMgr) Proxy.newProxyInstance(UserMgr.class, h);
        u.addUser();
    }
}
