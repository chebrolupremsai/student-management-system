package com.example.legacy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/legacy/students")
public class StudentServlet extends HttpServlet {

    private JdbcStudentDao dao = new JdbcStudentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Student> students = dao.getAll();
            req.setAttribute("students", students);
            req.getRequestDispatcher("/students.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Database error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            Student s = new Student();
            s.setName(req.getParameter("name"));
            s.setEmail(req.getParameter("email"));
            s.setCourse(req.getParameter("course"));
            dao.add(s);
            resp.sendRedirect(req.getContextPath() + "/legacy/students");
        } catch (Exception e) {
            throw new RuntimeException("Insert error", e);
        }
    }
}