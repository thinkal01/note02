package com.note.old.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountDao {
    /**
     * 修改指定用户的余额！
     *
     * @param name
     * @param balance
     */
    public void updateBalance(Connection con, String name, double balance) {
        try {

            /*
             * 2. 给出sql模板，创建pstmt
             */
            String sql = "update account set balance=balance+? where name=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            /*
             * 3. 对参数进行赋值
             */
            pstmt.setDouble(1, balance);
            pstmt.setString(2, name);
            /*
             * 4. 执行之
             */
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
