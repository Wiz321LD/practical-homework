package org.example.hw4.service;

import org.example.hw4.dao.StudentDAO;
import org.example.hw4.model.Student;

import java.util.List;

public class StudentService {

    private static final StudentDAO STUDENT_DAO = StudentDAO.getInstance();

    private static final StudentService INSTANCE = new StudentService();


    private StudentService() {}




    public static StudentService getInstance() {
        return INSTANCE;
    }

}
