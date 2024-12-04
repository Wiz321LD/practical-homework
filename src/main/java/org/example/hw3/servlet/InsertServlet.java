package org.example.hw3.servlet;

import org.example.hw3.model.Student;
import org.example.hw3.model.UniversityGroup;
import org.example.hw3.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {

    private static final StudentService STUDENT_SERVICE = StudentService.getInstance();


    //Type: /insert?name=...&surname=...&date=yyyy-MM-dd&number=...
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        String birthdate = req.getParameter("date");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        try {
            date = formatter.parse(birthdate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        UniversityGroup group = new UniversityGroup();
        group.setNumber(Integer.parseInt(req.getParameter("number")));
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setBirthDate(date);
        student.setUniversityGroup(group);

        session.setAttribute("student", student);

        doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Student student = (Student) session.getAttribute("student");

        STUDENT_SERVICE.createOneNewStudent(student);

        try(PrintWriter outWriter = resp.getWriter()){
            outWriter.println("OK");
        }

    }

}
