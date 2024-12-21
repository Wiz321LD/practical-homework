package org.example.hw4.service;

import org.example.hw4.dao.TeacherDAO;
import org.example.hw4.model.Teacher;

import java.util.List;

public class TeacherService {

    private static final TeacherDAO TEACHER_DAO = TeacherDAO.getInstance();

    private static final TeacherService INSTANCE = new TeacherService();


    private TeacherService() {}


    //CREATE
    public void createOneNewTeacher(Teacher teacher) {
        TEACHER_DAO.insertTeacher(teacher);
    }

    //READ
    public Teacher getTeacherById(int id) {
        return TEACHER_DAO.selectTeacher(id);
    }

    //READ
    public List<Teacher> getAllTeachers() {
        return TEACHER_DAO.selectAllTeachers();
    }

    public static TeacherService getInstance() {
        return INSTANCE;
    }

}
