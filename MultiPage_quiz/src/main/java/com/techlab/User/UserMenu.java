package com.techlab.User;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userMenu")
public class UserMenu extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");

        HttpSession session = req.getSession(false); // better: don't auto-create

        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        session.setAttribute("questionCount", 0);

        pw.write("<!DOCTYPE html>");
        pw.write("<html><head><meta charset='UTF-8'><title>User Menu</title>");
        pw.write("<style>");
        pw.write("body {font-family: Arial, sans-serif; background: #f4f6f9; display:flex; justify-content:center; align-items:center; height:100vh; margin:0;}");
        pw.write(".container {background:#fff; padding:30px; border-radius:10px; box-shadow:0 4px 10px rgba(0,0,0,0.1); width:350px; text-align:center;}");
        pw.write("h2 {color:#333; margin-bottom:20px;}");
        pw.write("a {display:block; margin:12px 0; padding:10px; background:#4CAF50; color:white; text-decoration:none; border-radius:6px; font-size:16px;}");
        pw.write("a:hover {background:#45a049;}");
        pw.write("</style></head><body>");

        pw.write("<div class='container'>");
        pw.write("<h2>User Menu</h2>");
        pw.write("<a href='testInfo'>Start Test</a>");
        pw.write("<a href='dashboard'>Dashboard</a>");
        pw.write("<a href='logout' style='background:red;'>Logout</a>");
        pw.write("</div>");

        pw.write("</body></html>");
    }
}
