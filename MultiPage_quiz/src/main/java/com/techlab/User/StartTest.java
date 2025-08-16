package com.techlab.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.techlab.Admin.DAO.GetAllQuestion;
import com.techlab.Entity.Question;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/startTest")
public class StartTest extends HttpServlet {

    @SuppressWarnings("unchecked")
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.html");
            return;
        }

        Integer number = (Integer) session.getAttribute("questionCount");
        if (number == null) {
            number = 0; // start from first question
            session.setAttribute("questionCount", number);
        }

        List<Question> questions = GetAllQuestion.getAllQuestion();
        if (questions.isEmpty()) {
            pw.write("No questions are there.<br>");
            RequestDispatcher rd = req.getRequestDispatcher("userMenu");
            rd.include(req, resp);
            return;
        }

        Question question = questions.get(number);

        // Retrieve saved answers
        Map<String, String> answers = (Map<String, String>) session.getAttribute("userAnswers");
        String savedAnswer = (answers != null) ? answers.get(question.getQuestion()) : null;
        session.setAttribute("questionForUser", question.getQuestion());

        pw.write("""
        	    <!DOCTYPE html>
        	    <html>
        	    <head>
        	        <title>Quiz Test</title>
        	        <style>
        	            body {
        	                font-family: Arial, sans-serif;
        	                background: #f4f6f9;
        	                display: flex;
        	                justify-content: center;
        	                align-items: center;
        	                min-height: 100vh;
        	                margin: 0;
        	            }
        	            .container {
        	                background: #fff;
        	                padding: 30px;
        	                border-radius: 12px;
        	                box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        	                width: 600px;
        	                max-width: 90vw;
        	                text-align: left;
        	            }
        	            h2 { text-align: center; color: #333; margin-bottom: 20px; }

        	            /* Options layout */
        	            .options { display: flex; flex-direction: column; gap: 12px; }
        	            .option {
        	                display: grid;
        	                grid-template-columns: 22px 1fr; /* radio | text */
        	                column-gap: 12px;
        	                align-items: start;
        	                padding: 12px;
        	                border: 1px solid #ddd;
        	                border-radius: 8px;
        	                transition: background 0.2s, border-color 0.2s;
        	                cursor: pointer;
        	            }
        	            .option:hover { background: #f7faff; border-color: #bcd3ff; }

        	            .option input[type="radio"] {
        	                margin: 3px 0 0 0;   /* nudge down a bit to align with first text line */
        	            }
        	            .option span {
        	                display: block;
        	                line-height: 1.4;
        	                word-break: break-word; /* handle very long words */
        	            }

        	            .btn-container { margin-top: 20px; text-align: center; }
        	            button {
        	                padding: 10px 20px; margin: 5px; border: none; border-radius: 6px;
        	                cursor: pointer; font-size: 15px;
        	            }
        	            .back-btn { background: #333; color: #fff; }
        	            .back-btn:disabled { background: #888; cursor: not-allowed; }
        	            .next-btn { background: #4CAF50; color: #fff; }
        	            .next-btn:disabled { background: #9CCC9C; cursor: not-allowed; }
        	            .submit-btn { background: #d9534f; color: #fff; }
        	            .submit-btn:disabled { background: #e6a2a0; cursor: not-allowed; }
        	        </style>
        	    </head>
        	    <body>
        	        <div class="container">
        	            <h2>Question</h2>
        	            <form action="questionNav" method="post">
        	                <p><strong>%s</strong></p>

        	                <div class="options">
        	                    <label class="option">
        	                        <input type="radio" name="answer" value="%s" %s>
        	                        <span>%s</span>
        	                    </label>
        	                    <label class="option">
        	                        <input type="radio" name="answer" value="%s" %s>
        	                        <span>%s</span>
        	                    </label>
        	                    <label class="option">
        	                        <input type="radio" name="answer" value="%s" %s>
        	                        <span>%s</span>
        	                    </label>
        	                    <label class="option">
        	                        <input type="radio" name="answer" value="%s" %s>
        	                        <span>%s</span>
        	                    </label>
        	                </div>

        	                <div class="btn-container">
        	                    <button type="submit" class="back-btn" name="action" value="back" %s>Back</button>
        	                    <button type="submit" class="next-btn" name="action" value="next" %s>Next</button>
        	                    <button type="submit" class="submit-btn" name="action" value="submit" %s>Submit Test</button>
        	                </div>
        	            </form>
        	        </div>
        	    </body>
        	    </html>
        	""".formatted(
        	    question.getQuestion(),

        	    // Option 1
        	    question.getOp1(),
        	    question.getOp1().equals(savedAnswer) ? "checked" : "",
        	    question.getOp1(),

        	    // Option 2
        	    question.getOpt2(),
        	    question.getOpt2().equals(savedAnswer) ? "checked" : "",
        	    question.getOpt2(),

        	    // Option 3
        	    question.getOpt3(),
        	    question.getOpt3().equals(savedAnswer) ? "checked" : "",
        	    question.getOpt3(),

        	    // Option 4
        	    question.getOpt4(),
        	    question.getOpt4().equals(savedAnswer) ? "checked" : "",
        	    question.getOpt4(),

        	    // Disable buttons when needed
        	    number == 0 ? "disabled" : "",
        	    number == questions.size() - 1 ? "disabled" : "",
        	    number == 0 ? "disabled" : ""
        	));

    }
}
