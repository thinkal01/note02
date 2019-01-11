package com.note.old.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {
/*    private static Properties props = null;

    // 只在JdbcUtils类被加载时执行一次！
    static {
        // 给props进行初始化，即加载dbconfig.properties文件到props对象中
        try {
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            props = new Properties();
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 加载驱动类
        try {
            Class.forName(props.getProperty("driverClassName"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 获取连接
    public static Connection getConnection() throws SQLException {
        // 得到Connection
        return DriverManager.getConnection(props.getProperty("url"),
                props.getProperty("username"),
                props.getProperty("password"));
    }*/

    // 配置文件的默认配置！要求你必须给出c3p0-config.xml！！！
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    /**
     * 使用连接池返回一个连接对象
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 返回连接池对象！
     *
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }
}
