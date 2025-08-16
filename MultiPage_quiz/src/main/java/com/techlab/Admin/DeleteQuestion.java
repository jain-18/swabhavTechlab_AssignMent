package com.techlab.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.techlab.Admin.DAO.DeleteQuestionFromDB;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteQuestion")
public class DeleteQuestion extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.html");
            return;
        }
        int id = Integer.parseInt(req.getParameter("question"));
        try {
			int result = DeleteQuestionFromDB.deleteQuestion(id);
			if(result == 1) {
				pw.write("successfully deleted question");
				RequestDispatcher rd = req.getRequestDispatcher("viewQuestions");
				rd.include(req, resp);
			}else {
				pw.write("Something went wrong");
				RequestDispatcher rd = req.getRequestDispatcher("viewQuestions");
				rd.include(req, resp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
