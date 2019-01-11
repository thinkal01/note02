package com.note.old.itcast_framework.tx;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TxConnectionManager {
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    private static ThreadLocal<Boolean> isTx = new ThreadLocal<>();

    static {
        isTx.set(false);
    }

    public static boolean isTx() {
        return isTx.get();
    }

    public static void setTx(boolean isTx) {
        TxConnectionManager.isTx.set(isTx);
    }

    public static void releaseConnection(Connection con) throws SQLException {
        if (!isTx.get()) {
            close(con);
        }
    }

    public static void close(Connection con) throws SQLException {
        if (con != null && con == tl.get()) {
            con.close();
            tl.remove();
        }
    }

    public synchronized static Connection getConnection(DataSource dataSource) throws SQLException {
        Connection con = tl.get();
        if (con == null) {
            con = dataSource.getConnection();
            if (isTx.get()) {
                con.setAutoCommit(false);
            }
            tl.set(con);
        }
        return con;
    }
}
