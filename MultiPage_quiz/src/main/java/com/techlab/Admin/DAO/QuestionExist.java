package com.techlab.Admin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.techlab.service.MyConnection;

public class QuestionExist {
	public static boolean isQuestionExist(String question) throws ClassNotFoundException, SQLException {
		String query = "Select * from questions where question = ?";
		try(Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, question);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		}
	}
}
