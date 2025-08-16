package com.techlab.Admin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.techlab.service.MyConnection;

public class DeleteQuestionFromDB {

	public static int deleteQuestion(int id) throws ClassNotFoundException, SQLException {
		String query = "Delete from questions where id = ?";
		try (Connection conn = MyConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			if(result==1) {
				return 1;
			}
			return -1;	
		}

	}

}
