package org.example.hw4.dao;

import org.example.hw4.connection.HibernateSessionManager;
import org.example.hw4.model.Student;
import org.example.hw4.model.Teacher;
import org.example.hw4.model.UniversityGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TeacherDAO implements SimpleDAO<Integer, Teacher>{

    private static final SessionFactory SESSION_FACTORY = HibernateSessionManager.getSessionFactory();
    private static final TeacherDAO TEACHER_DAO_INSTANCE = new TeacherDAO();


    private TeacherDAO(){}


    public static TeacherDAO getInstance(){
        return TEACHER_DAO_INSTANCE;
    }

    @Override
    public Teacher save(Teacher element) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.persist(element);
            session.getTransaction().commit();
        }
        return element;
    }

    @Override
    public Optional<Teacher> findById(Integer id) {
        Optional<Teacher> teacherOptional = Optional.empty();
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            teacherOptional = Optional.ofNullable(session.get(Teacher.class, id));
            session.getTransaction().commit();
        }
        return teacherOptional;
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teacherList = new ArrayList<>();
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            teacherList = session.createQuery("FROM Teacher", Teacher.class).getResultList();
        }
        return teacherList;
    }

    @Override
    public void update(Teacher element) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            if (Objects.isNull(session.find(Teacher.class, element.getTeacherId()))){
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
            session.remove(session.get(Teacher.class, id));
            session.getTransaction().commit();
        }
    }

}
