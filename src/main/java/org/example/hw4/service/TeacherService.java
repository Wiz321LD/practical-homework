package org.example.hw4.service;

import org.example.hw4.dao.TeacherDAO;
import org.example.hw4.model.Teacher;

import java.util.List;

public class TeacherService {

    private static final TeacherDAO TEACHER_DAO = TeacherDAO.getInstance();

    private static final TeacherService INSTANCE = new TeacherService();


    private TeacherService() {}




    public static TeacherService getInstance() {
        return INSTANCE;
    }

}
