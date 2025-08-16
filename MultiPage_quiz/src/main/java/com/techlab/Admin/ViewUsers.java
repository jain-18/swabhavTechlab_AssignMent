package com.techlab.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.techlab.Admin.DAO.GetAllUser;
import com.techlab.Entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewUsers")
public class ViewUsers extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");

        HttpSession session = req.getSession(false); // prevent creating new session

        if (session == null) {
            pw.write("session expired!!");
            resp.sendRedirect("login.html");
            return;
        }

        List<User> users = null;
        try {
            users = GetAllUser.getUsers();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        pw.println("""
        <!DOCTYPE html>
        <html>
        <head>
            <title>All Users</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background: #f4f6f9;
                    margin: 0;
                    padding: 20px;
                }
                h2 {
                    text-align: center;
                    color: #333;
                    margin-bottom: 20px;
                }
                .container {
                    width: 80%;
                    margin: auto;
                    background: #fff;
                    padding: 20px;
                    border-radius: 10px;
                    box-shadow: 0 4px 10px rgba(0,0,0,0.1);
                }
                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                }
                th, td {
                    padding: 12px;
                    text-align: left;
                    border-bottom: 1px solid #ddd;
                }
                th {
                    background: #2196F3;
                    color: white;
                }
                tr:hover {
                    background-color: #f1f1f1;
                }
                .btn-delete {
                    background: #f44336;
                    color: white;
                    padding: 6px 12px;
                    border: none;
                    border-radius: 5px;
                    cursor: pointer;
                }
                .btn-delete:hover {
                    background: #d32f2f;
                }
                .back-link {
                    display: inline-block;
                    margin-bottom: 15px;
                    text-decoration: none;
                    background: #4CAF50;
                    color: white;
                    padding: 8px 15px;
                    border-radius: 6px;
                }
                .back-link:hover {
                    background: #388E3C;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <a class="back-link" href='adminMenu'>⬅ Back to Menu</a>
                <h2>All Users</h2>
                <table>
                    <tr>
                        <th>User Name</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
        """);

        if (users != null) {
            for (User u : users) {
                pw.println("<tr>");
                pw.println("<td>" + u.getUserName() + "</td>");
                pw.println("<td>" + u.getEmailId() + "</td>");
                pw.println("<td>");
                pw.println("<form action='deleteUser' method='post' style='display:inline;'>");
                pw.println("<input type='hidden' name='user' value=\"" + u.getUserId() + "\">");
                pw.println("<input type='submit' value='Delete' class='btn-delete'>");
                pw.println("</form>");
                pw.println("</td>");
                pw.println("</tr>");
            }
        }

        pw.println("""
                </table>
            </div>
        </body>
        </html>
        """);
    }
}
