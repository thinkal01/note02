package com.note.test;

import com.note.service.impl.TestServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//@ContextConfiguration(locations = "classpath:applicationContext.xml")
//@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest01 {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TestServiceImpl testService;

    @Test
    public void test01() {
        Class<? extends DataSource> dClass = dataSource.getClass();
        String dName = dClass.getName();
        String dStr = dClass.toString();

        Class<? extends JdbcTemplate> jClass = jdbcTemplate.getClass();
        String name = jClass.getName();
        String className = jClass.toString();
    }

    @Test
    public void test02() throws SQLException {
        // com.mchange.v2.c3p0.impl.NewProxyConnection
        Connection connection = dataSource.getConnection();
        Statement st = connection.createStatement();
        st.executeUpdate("insert into test01 values(2,'name01',1000)");
        int i = 1 / 0;
        st.close();
        connection.close();
        String name = connection.getClass().getName();
        System.out.println(name);
    }

    @Test
    public void test03() {
        // jdbcTemplate.update("insert into test01 values(3,'name01',1000)");
        testService.testTx();
    }

    @Test
    public void test04() {
        // test03();
        // int i = 1 / 0;
        // test03();
    }

    @Test
    public void test05() {
        File file = new File("1.txt");
        String absolutePath = file.getAbsolutePath();
    }

}