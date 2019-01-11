package com.note.old.jdbc;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * c3p0
 * @author cxf
 *
 */
public class Demo8 {
	/**
	 * 代码配置
	 * @throws PropertyVetoException
	 * @throws SQLException
	 */
	@Test
	public void fun1() throws PropertyVetoException, SQLException {
		// 创建连接池对象
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		// 对池进行四大参数的配置
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mydb3");
		dataSource.setUser("root");
		dataSource.setPassword("123");
		
		// 池配置
		dataSource.setAcquireIncrement(5);
		dataSource.setInitialPoolSize(20);
		dataSource.setMinPoolSize(2);
		dataSource.setMaxPoolSize(50);
		
		Connection con = dataSource.getConnection();
		System.out.println(con);
		con.close();
	}
	
	/**
	 * 配置文件的默认配置
	 * @throws SQLException 
	 */
	@Test
	public void fun2() throws SQLException{
		/**
		 * 在创建连接池对象时，这个对象就会自动加载配置文件！不用我们来指定
		 */
		ComboPooledDataSource dataSource  = new ComboPooledDataSource();
		
		Connection con = dataSource.getConnection();
		System.out.println(con);
		con.close();
	}
	
	/**
	 * 使用命名配置信息
	 * @throws SQLException
	 */
	@Test
	public void fun3() throws SQLException{
		/**
		 * 构造器的参数指定命名配置元素的名称！
		 * <named-config name="oracle-config"> 
		 */
		ComboPooledDataSource dataSource  = new ComboPooledDataSource("oracle-config");
		
		Connection con = dataSource.getConnection();
		System.out.println(con);
		con.close();
	}
}
