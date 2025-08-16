package com.techlab.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.techlab.Admin.DAO.GetAllQuestion;
import com.techlab.Entity.Question;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/correctAnswers")
public class CorrectAnswers extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.html");
            return;
        }

        // Get stored answers and questions
        Map<String, String> userSubmission = (Map<String, String>) session.getAttribute("checkingAnswers");
        List<Question> questions = GetAllQuestion.getAllQuestion();

        pw.write("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>Check Answers</title>
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
                        padding: 25px;
                        border-radius: 12px;
                        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                        width: 85%;
                        max-width: 950px;
                    }
                    h2 {
                        text-align: center;
                        margin-bottom: 20px;
                        color: #333;
                    }
                    .question-block {
                        margin-bottom: 25px;
                        padding: 15px;
                        border-bottom: 1px solid #ddd;
                    }
                    .question-text {
                        font-weight: bold;
                        margin-bottom: 10px;
                        color: #222;
                    }
                    .user-answer {
                        color: red;
                        margin-bottom: 6px;
                    }
                    .correct-answer {
                        color: green;
                        font-weight: bold;
                    }
                    .btn {
                        display: inline-block;
                        margin-top: 20px;
                        padding: 12px 20px;
                        background: #333;
                        color: white;
                        text-decoration: none;
                        border-radius: 6px;
                        transition: background 0.2s;
                    }
                    .btn:hover {
                        background: #555;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2>✅ Check Your Answers</h2>
        """);

        int qNo = 1;
        for (Question q : questions) {
            String userAns = userSubmission.getOrDefault(String.valueOf(q.getQuestion()), "Not Answered");
            String correctAns = q.getcOpt();

            pw.write("<div class='question-block'>");
            pw.write("<div class='question-text'>Q" + qNo + ". " + q.getcOpt() + "</div>");
            pw.write("<div class='user-answer'>Your Answer: " + userAns + "</div>");
            pw.write("<div class='correct-answer'>Correct Answer: " + correctAns + "</div>");
            pw.write("</div>");
            qNo++;
        }

        pw.write("""
                    <a href='userMenu' class='btn'>⬅ Back to Menu</a>
                </div>
            </body>
            </html>
        """);
    }
}
