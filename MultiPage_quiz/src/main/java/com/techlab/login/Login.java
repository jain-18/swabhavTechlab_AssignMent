package com.techlab.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.techlab.DBOperation.UserCredentialLogin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		
		HttpSession session = req.getSession();

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		
		
		
		if(email.trim().equals("admin@gmail.com") && password.trim().equals("admin")) {
//			RequestDispatcher rd = req.getRequestDispatcher("adminMenu");
//			rd.forward(req, resp);
			resp.sendRedirect("/MultiPage_quiz/adminMenu");
		}else {
			try {
				int result = UserCredentialLogin.loginUser(email,password);
				if(result == -1) {
					pw.write("<p style='color:red; text-align:center; font-weight:bold;'>Invalid credentials</p>");
					RequestDispatcher rd = req.getRequestDispatcher("login.html");
					rd.include(req, resp);

				}else {
					session.setAttribute("userId",result);
					resp.sendRedirect("/MultiPage_quiz/userMenu");
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
