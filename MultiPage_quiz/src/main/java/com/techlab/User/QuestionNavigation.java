package com.techlab.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/questionNav")
public class QuestionNavigation extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.html");
            return;
        }

        Integer count = (Integer) session.getAttribute("questionCount");
        if (count == null) count = 0;

        // ✅ Step 1: Save user’s selected answer
        String selectedAnswer = req.getParameter("answer");
        String questionAsked = (String)session.getAttribute("questionForUser");
        
        @SuppressWarnings("unchecked")
        Map<String, String> answers = (Map<String, String>) session.getAttribute("userAnswers");
        if (answers == null) {
            answers = new HashMap<>();
        }
        if (selectedAnswer != null) {
            answers.put(questionAsked, selectedAnswer);
        }
        session.setAttribute("userAnswers", answers);

        // ✅ Step 2: Navigate
        String action = req.getParameter("action");
        if ("back".equals(action)) {
            if (count > 0) count--;
        } else if ("next".equals(action)) {
            count++;
        } else if ("submit".equals(action)) {
            // Redirect to result page
            resp.sendRedirect("submitTest");
            return;
        }

        session.setAttribute("questionCount", count);
        resp.sendRedirect("startTest");
    }

}
