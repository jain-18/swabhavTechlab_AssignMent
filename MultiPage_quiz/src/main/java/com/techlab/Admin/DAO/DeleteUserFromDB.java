package com.techlab.Admin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.techlab.service.MyConnection;

public class DeleteUserFromDB {

	public static int deleteUser(int userId) throws ClassNotFoundException, SQLException {
		String query = "delete from users where id = ?";
		try(Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, userId);
			int result = ps.executeUpdate();
			return result;
		}
	}

}
