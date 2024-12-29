package org.example.hw4.dao;

import org.example.hw4.model.Student;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;


import java.util.List;

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

    @Test
    public void solvedProblems(){
        Student student = STUDENT_DAO.findById(3).get();
        List<Student> students = STUDENT_DAO.findAll();
        assertDoesNotThrow(() -> System.out.println(student.getTeachers()));
        assertDoesNotThrow(() -> System.out.println(students));
    }

    @Test
    public void testInsert100_000BatchRawsInTable(){
        STUDENT_DAO.insert100_000BatchRawsInTable();
    }

    @Test
    public void testDelete100_000BatchRawsInTable(){
        STUDENT_DAO.delete100_000BatchRawsInTable();
    }

    @Test
    public void testGetStudentWhereGradeMoreThan2000(){
        Student student = STUDENT_DAO.getStudentWhereGradeMoreThan2000();
        System.out.println(student);
    }

    @Test
    public void testGetStudentsWhereGradeMoreThan2000(){
        List<Student> students = STUDENT_DAO.getStudentsWhereGradeMoreThan2000();
        students.forEach(System.out::println);
    }

}