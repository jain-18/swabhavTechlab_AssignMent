package com.techlab.Admin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.techlab.Entity.Question;
import com.techlab.service.MyConnection;

public class GetAllQuestion {

    public static List<Question> getAllQuestion() {
        List<Question> questions = new ArrayList<>();

        String query = "SELECT * FROM questions";

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Question q = new Question();
                q.setId(Integer.parseInt(rs.getString("id")));
                q.setQuestion(rs.getString("question"));
                q.setOp1(rs.getString("opt1"));
                q.setOpt2(rs.getString("opt2"));
                q.setOpt3(rs.getString("opt3"));
                q.setOpt4(rs.getString("opt4"));
                q.setcOpt(rs.getString("copt"));

                questions.add(q);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }
}
