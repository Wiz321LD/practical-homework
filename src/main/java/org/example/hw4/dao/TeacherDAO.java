package org.example.hw4.dao;

import org.example.hw4.connection.HibernateSessionManager;
import org.example.hw4.model.Student;
import org.example.hw4.model.Teacher;
import org.example.hw4.model.UniversityGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TeacherDAO {

    private static final String SELECT_TEACHER_BY_ID = "SELECT * FROM teacher WHERE teacher_id = ?";
    private static final String SELECT_ALL_TEACHERS = "SELECT * FROM teacher";
    private static final String INSERT_NEW_TEACHER = """
            INSERT INTO teacher (name, surname, birth_date) VALUES (?, ?, ?)
            """;
    private static final String INSERT_NEW_STUDENT = """
            INSERT INTO student(name, surname, birth_date, group_number_fk) VALUES (?,?,?,?)
            """;
    private static final String INSERT_KEYS_INTO_STUDENT_TEACHER = """
            INSERT INTO student_teacher(student_id_fk, teacher_id_fk) VALUES (?, ?)
            """;
    private static final String JOIN_SELECT_STUDENTS_BY_COMPOSITE_KEY = """
            SELECT stud.* FROM student AS stud
                JOIN student_teacher AS sttch ON stud.student_id = sttch.student_id_fk
                JOIN teacher AS tch ON sttch.teacher_id_fk = tch.teacher_id
                WHERE tch.teacher_id = ?
            """;
    private static final String SELECT_GROUP_BY_STUD_ID = """
            SELECT ug.* FROM university_group AS ug
                JOIN student s ON ug.number = s.group_number_fk
                WHERE s.student_id = ?
            """;

    private static final TeacherDAO TEACHER_DAO_INSTANCE = new TeacherDAO();


    private TeacherDAO(){}


    //CREATE one teacher
    public void insertTeacher(Teacher teacher) {
        //TODO:
    }

    //READ one teacher
    public Teacher selectTeacher(int teacherId) {
        //TODO:
        return null;
    }

    //READ all teachers
    public List<Teacher> selectAllTeachers(){
        //TODO:
        return null;
    }

    public static TeacherDAO getInstance(){
        return TEACHER_DAO_INSTANCE;
    }

}
