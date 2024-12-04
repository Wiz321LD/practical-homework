package org.example.hw3.service;

import org.example.hw3.dao.StudentDAO;
import org.example.hw3.model.Student;

import java.util.List;

public class StudentService {

    private static final StudentDAO STUDENT_DAO = StudentDAO.getInstance();

    private static final StudentService INSTANCE = new StudentService();


    private StudentService() {}


    //CREATE
    public void createOneNewStudent(Student student) {
        STUDENT_DAO.insertStudent(student);
    }

    //READ
    public Student getStudentById(int id) {
        return STUDENT_DAO.selectStudentById(id);
    }

    //READ
    public List<Student> getAllStudents() {
        return STUDENT_DAO.selectAllStudents();
    }

    public static StudentService getInstance() {
        return INSTANCE;
    }

}
