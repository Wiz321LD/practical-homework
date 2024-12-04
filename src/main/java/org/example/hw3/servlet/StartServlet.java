package org.example.hw3.servlet;

import org.example.hw3.model.Student;
import org.example.hw3.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/start")
public class StartServlet extends HttpServlet {

    private static final StudentService STUDENT_SERVICE = StudentService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        int studentId = Integer.parseInt(req.getParameter("studentId"));
        Student student = STUDENT_SERVICE.getStudentById(studentId);
        System.out.println(student);

        req.setAttribute("student", student);
        session.setAttribute("student", student);

        req.getRequestDispatcher("/WEB-INF/start.jsp").forward(req, resp);
    }

}
