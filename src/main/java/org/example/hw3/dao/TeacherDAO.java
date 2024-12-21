package org.example.hw3.dao;

import org.example.hw3.connection.JdbcConnectionManager;
import org.example.hw3.model.Student;
import org.example.hw3.model.Teacher;
import org.example.hw3.model.UniversityGroup;

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
        try(
                Connection connection = JdbcConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_NEW_TEACHER)
                ){
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getSurname());
            statement.setDate(3, new java.sql.Date(teacher.getBirthDate().getTime()));
            statement.executeUpdate();

            Set<Student> students = teacher.getStudents();

            if (students != null && students.size() > 0){
                for (Student student : students){
                    try(PreparedStatement statement2 = connection.prepareStatement(INSERT_NEW_STUDENT)){
                        statement2.setString(1, student.getName());
                        statement2.setString(2, student.getSurname());
                        statement2.setDate(3, new java.sql.Date(student.getBirthDate().getTime()));
                        statement2.setInt(4, student.getUniversityGroup().getNumber());
                        statement2.executeUpdate();

                        try(PreparedStatement statement3 = connection.prepareStatement(INSERT_KEYS_INTO_STUDENT_TEACHER)){
                            statement3.setInt(1, student.getStudentId());
                            statement3.setInt(2, teacher.getTeacherId());
                            statement3.executeUpdate();
                        } catch (SQLException z){
                            throw new RuntimeException(z);
                        }
                    } catch (SQLException s){
                        throw new RuntimeException(s);
                    }
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //READ one teacher
    public Teacher selectTeacher(int teacherId) {
        Teacher teacher = null;
        try(
                Connection connection = JdbcConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_TEACHER_BY_ID)
                ){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int teachId = resultSet.getInt("teacher_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Date birthDate = resultSet.getDate("birth_date");
                Set<Student> studentsInsideTeacher = new HashSet<>();
                UniversityGroup studGroup = new UniversityGroup();

                //
                try(PreparedStatement statement2 = connection.prepareStatement(JOIN_SELECT_STUDENTS_BY_COMPOSITE_KEY)){
                    statement2.setInt(1, teachId);
                    ResultSet resultSet2 = statement2.executeQuery();
                    while (resultSet2.next()){
                        int studentId = resultSet2.getInt("student_id");
                        String studName = resultSet2.getString("name");
                        String studSurname = resultSet2.getString("surname");
                        Date studBirthDate = resultSet2.getDate("birth_date");

                        try(PreparedStatement statement3 = connection.prepareStatement(SELECT_GROUP_BY_STUD_ID)){
                            statement3.setInt(1, studentId);
                            ResultSet resultSet3 = statement3.executeQuery();
                            while (resultSet3.next()){
                                int groupId = resultSet3.getInt("number");
                                studGroup.setNumber(groupId);
                            }
                        } catch (SQLException e){
                            throw new RuntimeException(e);
                        }
                        Student s = new Student(studentId, studName, studSurname, studBirthDate, studGroup);
                        s.getUniversityGroup().getStudents().add(s);
                        studentsInsideTeacher.add(s);


                    }
                } catch (SQLException e){
                    throw new RuntimeException(e);
                }
                //

                teacher = new Teacher(teacherId, name, surname, birthDate, studentsInsideTeacher);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return teacher;
    }

    //READ all teachers
    public List<Teacher> selectAllTeachers(){
        List<Teacher> teachers = new ArrayList<>();
        try(
                Connection connection = JdbcConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TEACHERS)
                ){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int teacherId = resultSet.getInt("teacher_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Date birthDate = resultSet.getDate("birth_date");
                Set<Student> studentsInsideTeacher = new HashSet<>();
                UniversityGroup studGroup = new UniversityGroup();

                try(PreparedStatement statement2 = connection.prepareStatement(JOIN_SELECT_STUDENTS_BY_COMPOSITE_KEY)){
                    statement2.setInt(1, teacherId);
                    ResultSet resultSet2 = statement2.executeQuery();
                    while (resultSet2.next()){
                        int studentId = resultSet2.getInt("student_id");
                        String studName = resultSet2.getString("name");
                        String studSurname = resultSet2.getString("surname");
                        Date studBirthDate = resultSet2.getDate("birth_date");

                        try(PreparedStatement statement3 = connection.prepareStatement(SELECT_GROUP_BY_STUD_ID)){
                            statement3.setInt(1, studentId);
                            ResultSet resultSet3 = statement3.executeQuery();
                            while (resultSet3.next()){
                                int groupId = resultSet3.getInt("number");
                                studGroup.setNumber(groupId);
                            }
                        } catch (SQLException e){
                            throw new RuntimeException(e);
                        }

                        Student s = new Student(studentId, studName, studSurname, studBirthDate, studGroup);
                        s.getUniversityGroup().getStudents().add(s);
                        studentsInsideTeacher.add(s);

                    }
                } catch (SQLException e){
                    throw new RuntimeException(e);
                }


                Teacher t = new Teacher(teacherId, name, surname, birthDate, studentsInsideTeacher);
                teachers.add(t);

            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return teachers;
    }

    public static TeacherDAO getInstance(){
        return TEACHER_DAO_INSTANCE;
    }

}
