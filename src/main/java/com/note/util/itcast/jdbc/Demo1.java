package com.note.util.itcast.jdbc;


import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class Demo1 {
    private AccountDao dao = new AccountDao();

    public void serviceMethod() throws Exception {
        try {
            JdbcUtils.beginTransaction();

            dao.update("zs", -100);
            if (true) throw new RuntimeException();
            dao.update("ls", 100);

            JdbcUtils.commitTransaction();
        } catch (Exception e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException e1) {
            }
            throw e;
        }
    }
}

class AccountDao {
    public static void update(String name, double money) throws SQLException {
        QueryRunner qr = new TxQueryRunner();
        String sql = "update account set balance=balance+? where name=?";
        Object[] params = {money, name};

        // 保证多次调用使用的是同一个连接！
        qr.update(sql, params);
    }
}