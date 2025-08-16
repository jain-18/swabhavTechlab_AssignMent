package com.techlab.Admin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techlab.Entity.User;
import com.techlab.service.MyConnection;

public class GetAllUser {

	public static List<User> getUsers() throws ClassNotFoundException, SQLException {
		String query = "select * from users";
		try(Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while(rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("id"));
				u.setUserName(rs.getString("name"));
				u.setEmailId(rs.getString("email"));
				users.add(u);
			}
			return users;
		}
	}

}
