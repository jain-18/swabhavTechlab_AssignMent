package com.techlab.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminMenu")
public class Menu extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("login.html");
            return;
        }

        pw.write("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>Admin Menu</title>
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
                    .menu-container {
                        background: #fff;
                        padding: 30px 40px;
                        border-radius: 12px;
                        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                        text-align: center;
                        width: 400px;
                    }
                    h2 {
                        margin-bottom: 25px;
                        color: #333;
                    }
                    .menu a {
                        display: block;
                        text-decoration: none;
                        margin: 12px 0;
                        padding: 12px;
                        background: #2196F3;
                        color: white;
                        border-radius: 6px;
                        transition: background 0.3s;
                        font-weight: bold;
                    }
                    .menu a:hover {
                        background: #1976D2;
                    }
                    .logout {
                        background: #f44336 !important;
                    }
                    .logout:hover {
                        background: #d32f2f !important;
                    }
                </style>
            </head>
            <body>
                <div class="menu-container">
                    <h2>Admin Dashboard</h2>
                    <div class="menu">
                        <a href='addQuestion'>➕ Add Question</a>
                        <a href='viewQuestions'>📖 View Questions</a>
                        <a href='viewUsers'>👥 All Users</a>
                        <a class="logout" href='logout'>🚪 Logout</a>
                    </div>
                </div>
            </body>
            </html>
        """);
    }
}
