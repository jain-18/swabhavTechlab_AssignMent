package com.techlab.DBOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.techlab.service.MyConnection;

public class RegisterUser {
	public static int registerUser(String name, String cPassword, String email)
	        throws ClassNotFoundException, SQLException {

	    String query = "insert into users(name,password,email) VALUES(?, ?, ?)";

	    try (Connection conn = MyConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

	        ps.setString(1, name);
	        ps.setString(2, cPassword); 
	        ps.setString(3, email);

	        int rowsAffected = ps.executeUpdate();

	        if(rowsAffected==1) {
	        	return 1;
	        }
	    }

	    return -1; 
	}



}
