package com.note.old.itcast_framework.tx;

import com.note.old.itcast_framework.beanfactory.factorybean.FactoryBean;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class TransactionFactoryBean implements FactoryBean {
    private DataSource dataSource;
    private Object target;

    public TransactionFactoryBean() {

    }

    public Object getObject() {
        ClassLoader l = Thread.currentThread().getContextClassLoader();
        Class[] interfaces = target.getClass().getInterfaces();
        InvocationHandler ih = (proxy, method, args) -> {
            try {
                TxConnectionManager.setTx(true);
                TxStack.push(method);
                Object returnValue = method.invoke(target, args);
                TxStack.pop();
                if (TxStack.isEmpty()) {
                    Connection con = TxConnectionManager.getConnection(dataSource);
                    con.commit();
                    TxConnectionManager.close(con);
                    TxConnectionManager.setTx(false);
                }
                return returnValue;
            } catch (Throwable e) {
                Connection con = TxConnectionManager.getConnection(dataSource);
                con.rollback();
                TxConnectionManager.close(con);
                TxConnectionManager.setTx(false);
                throw new Throwable(e);
            }
        };

        return Proxy.newProxyInstance(l, interfaces, ih);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
