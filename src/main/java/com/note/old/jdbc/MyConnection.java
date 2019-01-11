package com.note.old.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class MyConnection extends ConnectionWrapper {
    public MyConnection(Connection con) {
        super(con);
    }

    public void close() throws SQLException {
        System.out.println("哈哈~");
        super.close();
    }
}
