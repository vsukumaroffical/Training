package com.sukumar.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static DBConnection instance = null;
	
	private DBConnection () {
		
	}
	
	public static DBConnection getInstance() {
		if(instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {

		return DriverManager.getConnection(Configuration.DB_URL,Configuration.DB_USER,Configuration.DB_PASSWORD);
	}
	
}
