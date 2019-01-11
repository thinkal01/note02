package com.note.old.jdbc.itcast.staticProxy;

import java.sql.Connection;
import java.sql.SQLException;

public interface CloseListener {
	void run(Connection con) throws SQLException;
}
