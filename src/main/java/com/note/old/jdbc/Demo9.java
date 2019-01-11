package com.note.old.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * commons-dbutils
 * 简化jdbc的代码！
 */
public class Demo9 {
    @Test
    public void addStu(Stu stu) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtils.getConnection();

            String sql = "insert into t_user values(?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, stu.getSid());
            pstmt.setString(2, stu.getSname());
            pstmt.setInt(3, stu.getAge());
            pstmt.setString(4, stu.getGender());

            pstmt.executeUpdate();
        } catch (Exception e) {
            //处理异常
        } finally {
            //关闭
        }
    }

    @Test
    public void updateStu(Stu stu) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtils.getConnection();

            String sql = "update t_stu set sname=?, age=?, gender=? where sid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(4, stu.getSid());
            pstmt.setString(1, stu.getSname());
            pstmt.setInt(2, stu.getAge());
            pstmt.setString(3, stu.getGender());

            pstmt.executeUpdate();
        } catch (Exception e) {
            //处理异常
        } finally {
            //关闭
        }
    }

    @Test
    public void deleteStu(int sid) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtils.getConnection();

            String sql = "delete from t_stu where sid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, sid);

            pstmt.executeUpdate();
        } catch (Exception e) {
            //处理异常
        } finally {
            //关闭
        }
    }

    public Stu load(int sid) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JdbcUtils.getConnection();

            String sql = "select * from t_stu where sid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, sid);

            rs = pstmt.executeQuery();
            if (!rs.next()) return null;

            /*
             * 需要把rs转换成Stu对象
             * rs --> javabean
             */
            Stu stu = new Stu();
            stu.setSid(rs.getInt("sid"));
            stu.setSname(rs.getString("sname"));
            stu.setAge(rs.getInt("age"));
            stu.setGender(rs.getString("gender"));

            return stu;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //关闭
        }
    }


}
