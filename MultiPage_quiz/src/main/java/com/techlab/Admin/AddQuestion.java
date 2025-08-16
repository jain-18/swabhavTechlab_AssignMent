package com.techlab.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addQuestion")
public class AddQuestion extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.html");
            return;
        }

        pw.write("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>Add Question</title>
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
                    .container {
                        background: #fff;
                        padding: 30px;
                        border-radius: 12px;
                        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                        width: 500px;
                        max-width: 95vw;
                    }
                    h2 {
                        text-align: center;
                        margin-bottom: 20px;
                        color: #333;
                    }
                    label {
                        display: block;
                        margin: 15px 0 5px;
                        font-weight: bold;
                        color: #555;
                    }
                    textarea, input[type="text"] {
                        width: 100%;
                        padding: 10px;
                        border-radius: 6px;
                        border: 1px solid #ccc;
                        font-size: 14px;
                        margin-bottom: 10px;
                    }
                    .options-group {
                        margin-top: 15px;
                    }
                    .options-group input[type="radio"] {
                        margin-right: 8px;
                    }
                    .btn-container {
                        display: flex;
                        justify-content: space-between;
                        margin-top: 20px;
                    }
                    input[type="submit"], input[type="reset"] {
                        padding: 10px 18px;
                        border: none;
                        border-radius: 6px;
                        cursor: pointer;
                        font-size: 14px;
                    }
                    input[type="submit"] {
                        background: #4CAF50;
                        color: #fff;
                    }
                    input[type="submit"]:hover {
                        background: #45a049;
                    }
                    input[type="reset"] {
                        background: #f44336;
                        color: #fff;
                    }
                    input[type="reset"]:hover {
                        background: #d73833;
                    }
                    a {
                        display: inline-block;
                        margin-top: 20px;
                        text-decoration: none;
                        color: #2196F3;
                        font-weight: bold;
                    }
                    a:hover {
                        text-decoration: underline;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2>Add New Question</h2>
                    <form id="questionForm" action="addQuestionToDB" method="post">
                        <label>Question:</label>
                        <textarea rows="3" name="question" required></textarea>

                        <label>Option 1:</label>
                        <input type="text" name="opt1" required>

                        <label>Option 2:</label>
                        <input type="text" name="opt2" required>

                        <label>Option 3:</label>
                        <input type="text" name="opt3" required>

                        <label>Option 4:</label>
                        <input type="text" name="opt4" required>

                        <label>Correct Option:</label>
                        <div class="options-group">
                            <input type="radio" name="copt" value="opt1"> Option 1
                            <input type="radio" name="copt" value="opt2"> Option 2
                            <input type="radio" name="copt" value="opt3"> Option 3
                            <input type="radio" name="copt" value="opt4"> Option 4
                        </div>

                        <div class="btn-container">
                            <input type="reset" value="Reset">
                            <input type="submit" value="Submit">
                        </div>
                        <a href='adminMenu'>⬅ Back to Menu</a>
                    </form>
                </div>

                <script>
                    document.getElementById("questionForm").addEventListener("submit", function(e) {
                        let opt1 = document.querySelector("[name='opt1']").value.trim().toLowerCase();
                        let opt2 = document.querySelector("[name='opt2']").value.trim().toLowerCase();
                        let opt3 = document.querySelector("[name='opt3']").value.trim().toLowerCase();
                        let opt4 = document.querySelector("[name='opt4']").value.trim().toLowerCase();

                        let options = [opt1, opt2, opt3, opt4];
                        let uniqueOptions = new Set(options);

                        if (uniqueOptions.size < options.length) {
                            alert("All options must be different!");
                            e.preventDefault();
                            return;
                        }

                        let selectedRadio = document.querySelector("input[name='copt']:checked");
                        if (!selectedRadio) {
                            alert("Please select a correct option!");
                            e.preventDefault();
                            return;
                        }
                    });
                </script>
            </body>
            </html>
        """);
    }
}
