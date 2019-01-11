package com.note.old.itcast_framework.beanfactory.servlet;

import com.note.old.itcast_framework.beanfactory.BeanFactory;
import com.note.old.itcast_framework.beanfactory.web.utils.IocUtils;

import javax.servlet.http.HttpServlet;

public class BeanFactoryServlet extends HttpServlet {
    public void init() {
        String beanFactoryConfig = this.getInitParameter("beanFactoryConfig");
        BeanFactory factory = new BeanFactory(beanFactoryConfig);
        IocUtils.add(this.getServletContext(), factory);
    }
}
