package com.note.old.jdbc;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo10 {
    @Test
    public void fun1() {
//		Stu s = new Stu(1001, "zhangSan", 99, "male");
//		addStu(s);

        Stu s = load(1001);
        System.out.println(s);
    }

    public void addStu(Stu stu) {
        QR qr = new QR(JdbcUtils.getDataSource());//创建对象时给出连接池
        String sql = "insert into t_stu values(?,?,?,?)";//给出sql模板
        // 给出参数
        Object[] params = {stu.getSid(), stu.getSname(), stu.getAge(), stu.getGender()};
        // 调用update执行增、删、改！
        qr.update(sql, params);
    }

    public Stu load(int sid) {
        QR qr = new QR(JdbcUtils.getDataSource());//创建对象时给出连接池
        String sql = "select * from t_stu where sid=?";//给出sql模板
        Object[] params = {sid};

        RsHandler<Stu> rh = new RsHandler<Stu>() {
            public Stu handle(ResultSet rs) throws SQLException {
                if (!rs.next()) return null;
                Stu stu = new Stu();
                stu.setSid(rs.getInt("sid"));
                stu.setSname(rs.getString("sname"));
                stu.setAge(rs.getInt("age"));
                stu.setGender(rs.getString("gender"));
                return stu;
            }
        };
        return (Stu) qr.query(sql, rh, params);
    }
}
