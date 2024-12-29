package org.example.hw4.service;

import org.example.hw4.dao.StudentDAO;
import org.example.hw4.model.Student;

import java.util.List;

public class StudentService implements SimpleService<Integer, Student> {

    private static final StudentDAO STUDENT_DAO = StudentDAO.getInstance();
    private static final StudentService STUDENT_SERVICE = new StudentService();


    private StudentService() {}


    public static StudentService getStudentService() {
        return STUDENT_SERVICE;
    }

    @Override
    public Student create(Student element) {
        return STUDENT_DAO.save(element);
    }

    @Override
    public Student findById(Integer id) {
        return STUDENT_DAO.findById(id).orElse(null);
    }

    @Override
    public List<Student> findAll() {
        return STUDENT_DAO.findAll();
    }

    @Override
    public void update(Student element) {
        STUDENT_DAO.update(element);
    }

    @Override
    public void delete(Integer id) {
        STUDENT_DAO.delete(id);
    }

}
