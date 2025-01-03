package org.example.hw5.dao;

import org.example.hw5.config.ApplicationConfiguration;
import org.example.hw5.model.Student;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(ApplicationConfiguration.class)
class StudentDAOTest {


    private final SimpleDAO<Integer, Student> STUDENT_SIMPLE_DAO;
    private final StudentHardInsertDeleteDAO<Integer, Student> STUDENT_HARD_DAO;


    @Autowired
    StudentDAOTest(@Qualifier("studentDAO") SimpleDAO<Integer, Student> studentSimpleDao,
                   @Qualifier("studentDAO") StudentHardInsertDeleteDAO<Integer, Student> studentHardDao) {
        STUDENT_SIMPLE_DAO = studentSimpleDao;
        STUDENT_HARD_DAO = studentHardDao;
    }


    @Test
    public void testNPlusOneProblem() {
        Student student = STUDENT_HARD_DAO.findStudentAndTeachers(3);
        System.out.println(student);
    }

    @Test
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void testLazyInitialization() {
        Student student = STUDENT_SIMPLE_DAO.findById(3).get();
        assertThrows(LazyInitializationException.class, () -> System.out.println(student.getTeachers()));
    }

    @Test
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void solvedProblems(){
        Student student = STUDENT_SIMPLE_DAO.findById(3).get();
        List<Student> students = STUDENT_SIMPLE_DAO.findAll();
        assertDoesNotThrow(() -> System.out.println(student.getTeachers()));
        assertDoesNotThrow(() -> System.out.println(students));
    }

    @Test
    public void testInsert100_000BatchRawsInTable(){
        STUDENT_HARD_DAO.insert100_000BatchRawsInTable();
    }

    @Test
    public void testDelete100_000BatchRawsInTable(){
        STUDENT_HARD_DAO.delete100_000BatchRawsInTable();
    }

    @Test
    public void testGetStudentWhereGradeMoreThan2000(){
        Student student = STUDENT_HARD_DAO.getStudentWhereGradeMoreThan2000();
        System.out.println(student);
    }

    @Test
    public void testGetStudentsWhereGradeMoreThan2000(){
        List<Student> students = STUDENT_HARD_DAO.getStudentsWhereGradeMoreThan2000();
        students.forEach(System.out::println);
    }

}