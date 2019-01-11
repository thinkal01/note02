package com.note.util.itcast.jdbc;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 使用本类的方法，必须提供c3p0-copnfig.xml文件
 * DataSource需要从配置文件获取！它可能是
 * 1 DBCP连接池
 * 2 C3P0连接池
 * 3 XXX连接池
 * 4 从JNDI查找到的资源
 */
public class JdbcUtils {
    // 饿汉式
    // private static DataSource dataSource = new ComboPooledDataSource();
    private static DataSource dataSource = null;

    /**
     * 它为null表示没有事务
     * 它不为null表示有事务
     * 当开启事务时，需要给它赋值
     * 当结束事务时，需要给它赋值为null
     * 并且在开启事务时，让dao的多个方法共享这个Connection
     */
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    public static DataSource getDataSource() {
        return dataSource;
    }

    static {
        init();
    }

    // 这个方法只是把配置文件加载到Properties中
    // 分析当前使用的方式：是JNDI，还是创建连接池
    // 如果是JNDI，他去调用indi方法
    // 如果是创建连接池，那么它去调用createDataSource()方法
    private static void init() {
        try {
            // 把配置文件加载到Properites中
            Properties prop = new Properties();
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("dbconfig.properties");
            prop.load(in);

            // 判断使用的方式
            String jndiName = prop.getProperty("jndiName");
            if (jndiName == null || jndiName.isEmpty()) {
                //它会在配置信息中找到连接池的类名称
                //通过类名创建连接池对象，即初始化我们的连接池属性了
                //把配置信息中的其它配置，给连接池的属性赋值。
                createDataSource(prop);
            } else {
                jndi(jndiName);//它会通过jndiName找到连接池，即初始化我们的连接池属性
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void createDataSource(Properties prop) throws Exception {
        /*
         * 1获取连接池的类名
         * 2创建连接池对象
         * 3初始化
         */
        String dataSourceClassName = prop.getProperty("dataSourceClassName");
        if (dataSourceClassName == null || dataSourceClassName.isEmpty()) {
            throw new RuntimeException("你不配置jndi，也不配置dataSourceClassName，你让我配置啊！");
        }
        // 通过类名得到Class对象
        Class clazz = Class.forName(dataSourceClassName);
        dataSource = (DataSource) clazz.newInstance();
        // BeanUtils.populate(dataSource, prop);
    }

    private static void jndi(String jndiName) throws NamingException {
        // 创建上下文
        Context cxt = new InitialContext();
        // 查找资源，给我们的dataSource赋值
        dataSource = (DataSource) cxt.lookup("java:/comp/env/" + jndiName);
    }

    /**
     * dao使用本方法来获取连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        /*
         * 如果有事务，返回当前事务的con
         * 如果没有事务，通过连接池返回新的con
         */
        Connection con = connectionHolder.get();//获取当前线程的事务连接
        if (con != null) return con;
        return dataSource.getConnection();
    }

    /**
     * 开启事务
     *
     * @throws SQLException
     */
    public static void beginTransaction() throws SQLException {
        Connection con = connectionHolder.get();//获取当前线程的事务连接
        if (con != null) throw new SQLException("已经开启了事务，不能重复开启！");
        con = dataSource.getConnection();//给con赋值，表示开启了事务
        con.setAutoCommit(false);//设置为手动提交,开启事务
        connectionHolder.set(con);//给当前线程设置一个连接对象
    }

    /**
     * 提交事务
     *
     * @throws SQLException
     */
    public static void commitTransaction() throws SQLException {
        Connection con = connectionHolder.get();//获取当前线程的事务连接
        if (con == null) throw new SQLException("没有事务不能提交！");
        con.commit();//提交事务
        con.close();//关闭连接
        con = null;//表示事务结束！
        connectionHolder.remove();
    }

    /**
     * 回滚事务
     *
     * @throws SQLException
     */
    public static void rollbackTransaction() throws SQLException {
        Connection con = connectionHolder.get();//获取当前线程的事务连接
        if (con == null) throw new SQLException("没有事务不能回滚！");
        con.rollback();
        con.close();
        con = null;
        connectionHolder.remove();
    }

    /**
     * 释放Connection
     *
     * @param connection
     * @throws SQLException
     */
    public static void releaseConnection(Connection connection) throws SQLException {
        Connection con = connectionHolder.get();//获取当前线程的事务连接
        if (connection != con) {//如果参数连接，与当前事务连接不同，说明这个连接不是当前事务，可以关闭！
            if (connection != null && !connection.isClosed()) {//如果参数连接没有关闭，关闭之！
                connection.close();
            }
        }
    }

    public static void close(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
        }
    }

    public static void close(Connection con, Statement stmt) {
        close(con, stmt, null);
    }
}
