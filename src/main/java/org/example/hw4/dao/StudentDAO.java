package org.example.hw4.dao;

import org.example.hw4.connection.HibernateSessionManager;
import org.example.hw4.model.Student;
import org.example.hw4.model.UniversityGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentDAO implements SimpleDAO<Integer, Student>{

    private static final SessionFactory SESSION_FACTORY = HibernateSessionManager.getSessionFactory();
    private static final StudentDAO STUDENT_DAO_INSTANCE = new StudentDAO();


    private StudentDAO() {}


    public static StudentDAO getInstance(){
        return STUDENT_DAO_INSTANCE;
    }

    @Override
    public Student save(Student element) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.persist(element);
            session.getTransaction().commit();
        }
        return element;
    }

    @Override
    public Optional<Student> findById(Integer id) {
        Optional<Student> studentOptional = Optional.empty();
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            studentOptional = Optional.ofNullable(session.get(Student.class, id));
            session.getTransaction().commit();
        }
        return studentOptional;
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            studentList = session.createQuery("FROM Student", Student.class).getResultList();
        }
        return studentList;
    }

    @Override
    public void update(Student element) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            if (Objects.isNull(session.find(Student.class, element.getStudentId()))){
                session.persist(element);
            } else {
                session.merge(element);
            }

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.remove(session.get(Student.class, id));
            session.getTransaction().commit();
        }
    }
}
