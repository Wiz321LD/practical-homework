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

public class UniversityGroupDAO implements SimpleDAO<Integer, UniversityGroup>{

    private static final SessionFactory SESSION_FACTORY = HibernateSessionManager.getSessionFactory();
    private static final UniversityGroupDAO UNIVERSITY_GROUP_DAO_INSTANCE = new UniversityGroupDAO();


    private UniversityGroupDAO(){}


    public static UniversityGroupDAO getInstance() {
        return UNIVERSITY_GROUP_DAO_INSTANCE;
    }

    @Override
    public UniversityGroup save(UniversityGroup element) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.persist(element);
            session.getTransaction().commit();
        }
        return element;
    }

    @Override
    public Optional<UniversityGroup> findById(Integer id) {
        Optional<UniversityGroup> groupOptional = Optional.empty();
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            groupOptional = Optional.ofNullable(session.get(UniversityGroup.class, id));
            session.getTransaction().commit();
        }
        return groupOptional;
    }

    @Override
    public List<UniversityGroup> findAll() {
        List<UniversityGroup> groupList = new ArrayList<>();
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            groupList = session.createQuery("FROM UniversityGroup", UniversityGroup.class).getResultList();
            session.getTransaction().commit();
        }
        return groupList;
    }

    @Override
    public void update(UniversityGroup element) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            if (Objects.isNull(session.find(UniversityGroup.class, element.getNumber()))){
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
            session.remove(session.get(UniversityGroup.class, id));
            session.getTransaction().commit();
        }
    }

}
