package com.note.old.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 演示转账
 *
 * @author cxf
 * <p>
 * 所有对Connection的操作都在service层进行的处理！
 * 明天要去处理这一问题，把所有对Connection的操作隐藏起来！这需要使用自定义的小工具！
 */
public class Demo6 {
    /**
     * 转账方法
     *
     * @param from
     * @param to
     * @param money
     */
    public void zhuanZhang(String from, String to, double money) {
        // 对事务的操作必须使用Connection对象!
        Connection con = null;
        try {
            con = JdbcUtils.getConnection();
            //开启事务
            con.setAutoCommit(false);

            AccountDao dao = new AccountDao();
            dao.updateBalance(con, from, -money);//给from减去相应金额

            if (true) {
                throw new RuntimeException("不好意思");
            }

            dao.updateBalance(con, to, money);//给to加上相应金额

            //提交事务
            con.commit();
            con.close();
        } catch (Exception e) {
            //回滚事务
            try {
                con.rollback();
                con.close();
            } catch (SQLException e1) {
            }
            throw new RuntimeException(e);
        }
    }

    @Test
    public void fun1() {
        zhuanZhang("zs", "ls", 100);
    }
}
