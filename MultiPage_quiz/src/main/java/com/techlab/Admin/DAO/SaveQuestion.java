package com.techlab.Admin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.techlab.service.MyConnection;

public class SaveQuestion {

	public static void addQuestion(String question, String opt1, String opt2, String opt3, String opt4, String copt) throws ClassNotFoundException {
	    String sql = "INSERT INTO questions (question, opt1, opt2, opt3, opt4, copt) VALUES (?, ?, ?, ?, ?, ?)";
	    
	    try (Connection conn = MyConnection.getConnection();  // Your DB connection method
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setString(1, question);
	        ps.setString(2, opt1);
	        ps.setString(3, opt2);
	        ps.setString(4, opt3);
	        ps.setString(5, opt4);
	        ps.setString(6, copt);
	        
	        int rows = ps.executeUpdate();
	        
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}
