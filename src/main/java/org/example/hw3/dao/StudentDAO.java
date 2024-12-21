package org.example.hw3.dao;

import org.example.hw3.connection.JdbcConnectionManager;
import org.example.hw3.model.Student;
import org.example.hw3.model.UniversityGroup;

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
        try(
                Connection connection = JdbcConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_NEW_STUDENT)
                ){
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setDate(3, new java.sql.Date(student.getBirthDate().getTime()));
            statement.setInt(4, student.getUniversityGroup().getNumber());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //READ one student
    public Student selectStudentById(int studentId) {
        Student student = null;
        try(
                Connection connection = JdbcConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_STUDENT_BY_ID)
                ){
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int studId = resultSet.getInt("student_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Date birthDate = resultSet.getDate("birth_date");
                int groupNumber = resultSet.getInt("group_number_fk");
                UniversityGroup group = new UniversityGroup();
                group.setNumber(groupNumber);

                student = new Student(studentId, name, surname, birthDate, group);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return student;
    }

    //READ all students
    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        try(
                Connection connection = JdbcConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STUDENTS)
                ){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Date birthDate = resultSet.getDate("birth_date");
                int groupNumber = resultSet.getInt("group_number_fk");
                UniversityGroup group = new UniversityGroup();
                group.setNumber(groupNumber);

                students.add(new Student(studentId, name, surname, birthDate, group));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return students;
    }

    public static StudentDAO getInstance(){
        return STUDENT_DAO_INSTANCE;
    }

}
