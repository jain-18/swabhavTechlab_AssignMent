package com.techlab.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.techlab.Admin.DAO.GetAllQuestion;
import com.techlab.Entity.Question;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewQuestions")
public class ViewQuestion extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.html");
            return;
        }

        List<Question> questions = GetAllQuestion.getAllQuestion();

        pw.println("""
        <!DOCTYPE html>
        <html>
        <head>
            <title>View Questions</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background: #f4f6f9;
                    margin: 0;
                    padding: 20px;
                }
                h2 {
                    text-align: center;
                    margin-bottom: 20px;
                    color: #333;
                }
                .container {
                    width: 90%;
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
                <h2>All Questions</h2>
                <table>
                    <tr>
                        <th>Question</th>
                        <th>Option 1</th>
                        <th>Option 2</th>
                        <th>Option 3</th>
                        <th>Option 4</th>
                        <th>Correct Option</th>
                        <th>Action</th>
                    </tr>
        """);

        for (Question q : questions) {
            pw.println("<tr>");
            pw.println("<td>" + q.getQuestion() + "</td>");
            pw.println("<td>" + q.getOp1() + "</td>");
            pw.println("<td>" + q.getOpt2() + "</td>");
            pw.println("<td>" + q.getOpt3() + "</td>");
            pw.println("<td>" + q.getOpt4() + "</td>");
            pw.println("<td>" + q.getcOpt() + "</td>");
            pw.println("<td>");
            pw.println("<form action='deleteQuestion' method='post' style='display:inline;'>");
            pw.println("<input type='hidden' name='question' value=\"" + q.getId() + "\">");
            pw.println("<input type='submit' value='Delete' class='btn-delete'>");
            pw.println("</form>");
            pw.println("</td>");
            pw.println("</tr>");
        }

        pw.println("""
                </table>
            </div>
        </body>
        </html>
        """);
    }
}
