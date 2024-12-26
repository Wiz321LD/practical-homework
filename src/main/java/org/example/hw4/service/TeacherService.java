package org.example.hw4.service;

import org.example.hw4.dao.TeacherDAO;
import org.example.hw4.model.Teacher;

import java.util.List;

public class TeacherService implements SimpleService<Integer, Teacher> {

    private static final TeacherDAO TEACHER_DAO = TeacherDAO.getInstance();
    private static final TeacherService TEACHER_SERVICE = new TeacherService();


    private TeacherService() {}


    public static TeacherService getTeacherService() {
        return TEACHER_SERVICE;
    }

    @Override
    public Teacher create(Teacher element) {
        return TEACHER_DAO.save(element);
    }

    @Override
    public Teacher findById(Integer id) {
        return TEACHER_DAO.findById(id).orElse(null);
    }

    @Override
    public List<Teacher> findAll() {
        return TEACHER_DAO.findAll();
    }

    @Override
    public void update(Teacher element) {
        TEACHER_DAO.update(element);
    }

    @Override
    public void delete(Integer id) {
        TEACHER_DAO.delete(id);
    }

}