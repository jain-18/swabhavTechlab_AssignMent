package com.techlab.DBOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.techlab.service.MyConnection;

public class SavedResponse {

	public static void saveMarks(int marks, Integer id,int totalMarks) throws ClassNotFoundException, SQLException {
		String query = "insert into dashboard(userid,marks,totalMarks,testtime) values(?,?,?,?)";
		try(Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, (int)id);
			ps.setInt(2, marks);
			ps.setInt(3, totalMarks);
			ps.setTimestamp(4, java.sql.Timestamp.valueOf(LocalDateTime.now()));
			 
			ps.executeUpdate();
		}
	}

}
