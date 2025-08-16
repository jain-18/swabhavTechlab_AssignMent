package com.techlab.DBOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techlab.Entity.Dashboard;
import com.techlab.service.MyConnection;

public class UserResult {

	public static List<Dashboard> getAllResults(int userId) throws ClassNotFoundException, SQLException {
		String query = "select * from dashboard where userid = ? order by testtime desc";
		try(Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			List<Dashboard> results = new ArrayList<Dashboard>();
			
			while(rs.next()) {
				Dashboard d = new Dashboard();
				d.setId(rs.getInt("dashid"));
				d.setUserId(rs.getInt("userId"));
				d.setMarks(rs.getInt("marks"));
				d.setTestTime(rs.getTimestamp("testtime"));
				d.setTotalMarks(rs.getInt("totalMarks"));
				results.add(d);
			}
			return results;
		}
	}

}
