package org.example.hw5.servlet;

import org.example.hw5.model.Student;
import org.example.hw5.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/select")
public class SelectServlet extends HttpServlet {

    private final StudentService STUDENT_SERVICE;

    @Autowired
    public SelectServlet(StudentService studentService) {
        STUDENT_SERVICE = studentService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        int studentId = Integer.parseInt(req.getParameter("studentId"));
        Student student = STUDENT_SERVICE.findById(studentId);
        System.out.println(student);

        session.setAttribute("student", student);

        req.getRequestDispatcher("/WEB-INF/select.jsp").forward(req, resp);

    }

}
