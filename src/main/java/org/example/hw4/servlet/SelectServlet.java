package org.example.hw4.servlet;

import org.example.hw4.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/select")
public class SelectServlet extends HttpServlet {

    private static final StudentService STUDENT_SERVICE = StudentService.getStudentService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        HttpSession session = req.getSession();
//
//        int studentId = Integer.parseInt(req.getParameter("studentId"));
//        Student student = STUDENT_SERVICE.getStudentById(studentId);
//        System.out.println(student);
//
//        session.setAttribute("student", student);
//
//        req.getRequestDispatcher("/WEB-INF/select.jsp").forward(req, resp);

    }

}
