package org.example.hw4.dao;

import org.example.hw4.model.Student;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {

    private static final StudentDAO STUDENT_DAO = StudentDAO.getInstance();


    @Test
    public void testNPlusOneProblem() {
        Student student = STUDENT_DAO.findStudentAndTeachers(3);
        System.out.println(student);
    }

    @Test
    public void testLazyInitialization() {
        Student student = STUDENT_DAO.findById(3).get();
        assertThrows(LazyInitializationException.class, () -> System.out.println(student.getTeachers()));
    }

}