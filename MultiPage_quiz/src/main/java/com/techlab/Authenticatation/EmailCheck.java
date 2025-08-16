package com.techlab.Authenticatation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.techlab.service.MyConnection;

public class EmailCheck {
	public static boolean isEmailAlreadyexist(String email) throws ClassNotFoundException, SQLException {
		String query = "select * from users where email = ?";
		try(Connection conn = MyConnection.getConnection();PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		return false;
		
	}
}
