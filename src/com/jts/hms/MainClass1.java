package com.jts.hms;

import java.sql.Connection;
import java.sql.SQLException;

public class MainClass1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
		Connection conn = DatabaseService.getConnection();
	}
}
