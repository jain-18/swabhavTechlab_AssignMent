package com.techlab.bank.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo", "root", "root");
		return connection;
	}

	public void rollback() throws ClassNotFoundException, SQLException {
		MyConnection.getConnection().rollback();
		
	}
}
