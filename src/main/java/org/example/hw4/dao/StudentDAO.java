package org.example.hw4.dao;

import org.example.hw4.connection.HibernateSessionManager;
import org.example.hw4.model.Student;
import org.example.hw4.model.UniversityGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDAO {

    private static final String SELECT_STUDENT_BY_ID = "SELECT * FROM student WHERE student_id = ?";
    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM student";
    private static final String INSERT_NEW_STUDENT = """
            INSERT INTO student(name, surname, birth_date, group_number_fk) VALUES (?,?,?,?)
            """;

    private static final StudentDAO STUDENT_DAO_INSTANCE = new StudentDAO();


    private StudentDAO() {}


    //CREATE one student
    public void insertStudent(Student student) {
        //TODO:
    }

    //READ one student
    public Student selectStudentById(int studentId) {
        //TODO:
        return null;
    }

    //READ all students
    public List<Student> selectAllStudents() {
        //TODO:
        return null;
    }

    public static StudentDAO getInstance(){
        return STUDENT_DAO_INSTANCE;
    }

}
