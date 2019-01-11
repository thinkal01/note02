package com.note.old.jdbc.itcast.jdbc;

import com.note.old.itcast_framework.beanfactory.BeanFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Demo1 {
    public static void main(String[] args) throws Exception {
        BeanFactory beanFactory = new BeanFactory("beans.xml");
        final ItcastDataSource ds = (ItcastDataSource) beanFactory.getBean("dataSource");
        Connection[] cons = new Connection[5];
        for (int i = 0; i < 5; i++) {
            cons[i] = ds.getConnection();
        }
        new Thread(() -> {
            try {
                Connection con = ds.getConnection();
                System.out.println(con);
            } catch (SQLException e) {
            }
        }).start();

        Thread.sleep(10000);
        cons[0].close();
    }
}
