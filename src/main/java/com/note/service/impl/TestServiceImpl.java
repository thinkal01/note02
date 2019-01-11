package com.note.service.impl;

import com.note.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Service("testService")
public class TestServiceImpl implements TestService {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void testTx() {
        try {
            Connection connection = dataSource.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate("insert into test01 values(2,'name01',1000)");
            int i = 1 / 0;
            st.close();
            connection.close();
            // jdbcTemplate.update("insert into test01 values(3,'name01',1000)");
            // jdbcTemplate.update("insert into test01 values(3,'name01',1000)");
        } catch (Exception e) {
        } finally {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}
