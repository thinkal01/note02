package com.note.old.itcast_framework.beanfactory.factorybean.jndi;

import com.note.old.itcast_framework.beanfactory.factorybean.FactoryBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JndiFactoryBean implements FactoryBean {
    private String jndiName;

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

    public Object getObject() throws NamingException {
        Context cxt = new InitialContext();
        return cxt.lookup("java:/comp/env/" + jndiName);
    }
}
