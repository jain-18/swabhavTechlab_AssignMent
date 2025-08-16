package com.techlab.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.techlab.DBOperation.UserResult;
import com.techlab.Entity.Dashboard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class ViewDashboard extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("login.html");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        List<Dashboard> allResults = null;
        try {
            allResults = UserResult.getAllResults(userId);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        pw.write("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>User Dashboard</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background: #f4f6f9;
                        margin: 0;
                        padding: 20px;
                        display: flex;
                        justify-content: center;
                    }
                    .container {
                        background: #fff;
                        padding: 30px;
                        border-radius: 12px;
                        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                        width: 80%;
                        max-width: 900px;
                    }
                    h2 {
                        text-align: center;
                        color: #333;
                        margin-bottom: 20px;
                    }
                    table {
                        width: 100%;
                        border-collapse: collapse;
                        margin-top: 20px;
                    }
                    table th, table td {
                        border: 1px solid #ddd;
                        padding: 12px;
                        text-align: center;
                    }
                    table th {
                        background-color: #4CAF50;
                        color: white;
                        font-weight: bold;
                    }
                    table tr:nth-child(even) {
                        background-color: #f9f9f9;
                    }
                    table tr:hover {
                        background-color: #f1f1f1;
                    }
                    .back-btn {
                        display: inline-block;
                        margin-top: 20px;
                        padding: 12px 20px;
                        background: #333;
                        color: white;
                        text-decoration: none;
                        border-radius: 6px;
                        transition: background 0.2s;
                    }
                    .back-btn:hover {
                        background: #555;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2>📊 Your Test Submissions</h2>
                    <table>
                        <tr>
                            <th>Marks Obtained</th>
                            <th>Total Marks</th>
                            <th>Time of Submission</th>
                        </tr>
        """);

        if (allResults != null) {
            for (Dashboard d : allResults) {
                pw.write("<tr>");
                pw.write("<td>" + d.getMarks() + "</td>");
                pw.write("<td>" + d.getTotalMarks() + "</td>");
                pw.write("<td>" + d.getTestTime() + "</td>");
                pw.write("</tr>");
            }
        }

        pw.write("""
                    </table>
                    <a href='userMenu' class='back-btn'>⬅ Back to Menu</a>
                </div>
            </body>
            </html>
        """);
    }
}
