package com.techlab.DBOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.techlab.service.MyConnection;

public class UserCredentialLogin {

	public static int loginUser(String email, String password) throws ClassNotFoundException, SQLException {
		String query = "Select * from users where email = ? and password = ?";
		try (Connection conn = MyConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("password").equals(password)) {
					return Integer.parseInt(rs.getString("id"));
				}else {
					return -1;
				}
			}
			return -1;
		}

	}

}
