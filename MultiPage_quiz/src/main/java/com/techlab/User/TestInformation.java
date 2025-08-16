package com.techlab.User;

import java.io.IOException;
import java.io.PrintWriter;

import com.techlab.Admin.DAO.GetAllQuestion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/testInfo")
public class TestInformation extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");

        HttpSession session = req.getSession();

        if (session == null) {
            pw.write("Session expired!!");
            resp.sendRedirect("login.html");
            return;
        }

        int totalQuestions = GetAllQuestion.getAllQuestion().size();

        pw.write("<!DOCTYPE html>");
        pw.write("<html><head><title>Test Info</title>");
        pw.write("<style>");
        pw.write("body {font-family: Arial, sans-serif; background:#f4f6f9; display:flex; justify-content:center; align-items:center; height:100vh; margin:0;}");
        pw.write(".container {background:white; padding:30px; border-radius:10px; box-shadow:0 4px 10px rgba(0,0,0,0.1); text-align:center; width:350px;}");
        pw.write("h2 {color:#333; margin-bottom:20px;}");
        pw.write(".btn {padding:10px 20px; border:none; border-radius:6px; cursor:pointer; font-size:16px; margin:10px;}");
        pw.write(".start-btn {background:#4CAF50; color:white;}");
        pw.write(".start-btn:hover {background:#45a049;}");
        pw.write(".back-btn {background:#333; color:white; text-decoration:none; display:inline-block;}");
        pw.write(".back-btn:hover {background:black;}");
        pw.write("</style>");
        pw.write("</head><body>");

        pw.write("<div class='container'>");
        pw.write("<h2>Test Information</h2>");
        pw.write("<p>Total Questions: " + totalQuestions + "</p>");

        pw.write("<form action='startTest' method='post' style='display:inline;'>");
        pw.write("<input type='submit' class='btn start-btn' value='Start Test'>");
        pw.write("</form>");

        pw.write("<a href='userMenu' class='btn back-btn'>Back to Menu</a>");
        pw.write("</div>");

        pw.write("</body></html>");
    }
}
