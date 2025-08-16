package com.techlab.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techlab.Admin.DAO.GetAllQuestion;
import com.techlab.DBOperation.SavedResponse;
import com.techlab.Entity.Question;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/submitTest")
public class SubmitTest extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setContentType("text/html");
	        PrintWriter pw = resp.getWriter();

	        HttpSession session = req.getSession(false);
	        if (session == null) {
	            resp.sendRedirect("login.html");
	            return;
	        }
	        Map<String,String> userSubmission = (Map<String,String>) session.getAttribute("userAnswers");
	        List<Question> questions = GetAllQuestion.getAllQuestion();
	        int marks = CheckAnswers.getMarks(userSubmission,questions);
	        pw.write("""
	        	    <!DOCTYPE html>
	        	    <html>
	        	    <head>
	        	        <title>Test Result</title>
	        	        <style>
	        	            body {
	        	                font-family: Arial, sans-serif;
	        	                background: #f4f6f9;
	        	                display: flex;
	        	                justify-content: center;
	        	                align-items: center;
	        	                height: 100vh;
	        	                margin: 0;
	        	            }
	        	            .container {
	        	                background: #fff;
	        	                padding: 40px;
	        	                border-radius: 12px;
	        	                box-shadow: 0 4px 12px rgba(0,0,0,0.1);
	        	                text-align: center;
	        	                width: 500px;
	        	                max-width: 90vw;
	        	            }
	        	            h1 {
	        	                margin-bottom: 20px;
	        	                color: #333;
	        	            }
	        	            .score {
	        	                font-size: 22px;
	        	                margin: 20px 0;
	        	                font-weight: bold;
	        	                color: #4CAF50;
	        	            }
	        	            .btn-container {
	        	                margin-top: 30px;
	        	                display: flex;
	        	                justify-content: center;
	        	                gap: 15px;
	        	            }
	        	            .btn {
	        	                padding: 12px 20px;
	        	                border: none;
	        	                border-radius: 6px;
	        	                font-size: 16px;
	        	                cursor: pointer;
	        	                transition: background 0.2s;
	        	                text-decoration: none;
	        	                display: inline-block;
	        	            }
	        	            .dashboard-btn {
	        	                background: #333;
	        	                color: #fff;
	        	            }
	        	            .dashboard-btn:hover {
	        	                background: #555;
	        	            }
	        	            .answers-btn {
	        	                background: #2196F3;
	        	                color: #fff;
	        	            }
	        	            .answers-btn:hover {
	        	                background: #0b7dda;
	        	            }
	        	        </style>
	        	    </head>
	        	    <body>
	        	        <div class="container">
	        	            <h1>Test Completed 🎉</h1>
	        	            <div class="score">Your Score: %d / %d</div>
	        	            <div class="btn-container">
	        		            <a href="dashboard" class="btn dashboard-btn">DashBoard</a>
	        	                <a href="userMenu" class="btn dashboard-btn">Menu</a>
	        	                <a href="correctAnswers" class="btn answers-btn">Check Correct Answers</a>
	        	            </div>
	        	        </div>
	        	    </body>
	        	    </html>
	        	""".formatted(marks, questions.size()));

	        Integer id = (Integer)session.getAttribute("userId");
	        Map<String, String> copySubmission = new HashMap<>(userSubmission);
	        session.setAttribute("checkingAnswers", copySubmission);
	        userSubmission.clear();
	        session.setAttribute("userAnswers", userSubmission);
	        try {
				SavedResponse.saveMarks(marks,id,questions.size());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	}
}








