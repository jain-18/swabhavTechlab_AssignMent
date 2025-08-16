package com.techlab.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.techlab.Admin.DAO.QuestionExist;
import com.techlab.Admin.DAO.SaveQuestion;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addQuestionToDB")
public class AddQuestionToDb extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setContentType("text/html");
	        PrintWriter pw = resp.getWriter();

	        HttpSession session = req.getSession(false);
	        if (session == null) {
	            resp.sendRedirect("login.html");
	            return; // prevent showing form after redirect
	        }
	        String question = req.getParameter("question").trim();
	        String opt1 = req.getParameter("opt1").trim().trim();
	        String opt2 = req.getParameter("opt2").trim();
	        String opt3 = req.getParameter("opt3").trim();
	        String opt4 = req.getParameter("opt4").trim();
	        String selected = req.getParameter("copt"); // opt1, opt2, opt3, or opt4
	        String correctAnswer = switch (selected) {
	        case "opt1" -> opt1;
	        case "opt2" -> opt2;
	        case "opt3" -> opt3;
	        case "opt4" -> opt4;
	        default -> "";
	    };
	        
	        
	        try {
				if(QuestionExist.isQuestionExist(question)) {
					pw.write("the question is already exist");
					RequestDispatcher rd = req.getRequestDispatcher("addQuestion");
					rd.include(req, resp);
				}else {
					SaveQuestion.addQuestion(question,opt1,opt2,opt3,opt4,correctAnswer);
					pw.write("SucessFully Added question");
					RequestDispatcher rd = req.getRequestDispatcher("addQuestion");
					rd.include(req, resp);
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
