package com.techlab.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.techlab.Admin.DAO.DeleteQuestionFromDB;
import com.techlab.Admin.DAO.DeleteUserFromDB;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.html");
            return;
        }
        int userId = Integer.parseInt(req.getParameter("user"));
        try {
			int result = DeleteUserFromDB.deleteUser(userId);
//			System.out.println(result);
			if (result == 1) {
			    pw.write("Successfully deleted user<br>");
			} else {
			    pw.write("Something went wrong<br>");
			}
			RequestDispatcher rd = req.getRequestDispatcher("viewUsers");
			rd.include(req, resp);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
