package com.techlab.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.techlab.Authenticatation.EmailCheck;
import com.techlab.DBOperation.RegisterUser;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class Registration extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");

		HttpSession session = req.getSession();

		if (session == null) {
			pw.write("session expired!!");
			resp.sendRedirect("login.html");
		}
		String name = req.getParameter("username").trim();
		String password = req.getParameter("password").trim();
		String cPassword = req.getParameter("cPassword").trim();
		String email = req.getParameter("email").trim();

		try {
			if (EmailCheck.isEmailAlreadyexist(email)) {
				pw.write("email already exists");
				RequestDispatcher rd = req.getRequestDispatcher("Registration.html");
				rd.include(req, resp);
			} else {
				if (cPassword.length() < 4) {
					pw.write("Password sholud contain atleast 4 characters!");
					RequestDispatcher rd = req.getRequestDispatcher("Registration.html");
					rd.include(req, resp);
				} else {
					int register = RegisterUser.registerUser(name, cPassword, email);
					if (register == 1) {
						pw.write("Successfully Register!!");
						RequestDispatcher rd = req.getRequestDispatcher("login.html");
						rd.include(req, resp);
					} else {
						pw.write("Something went wrong!!");
						RequestDispatcher rd = req.getRequestDispatcher("Registration.html");
						rd.include(req, resp);
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
