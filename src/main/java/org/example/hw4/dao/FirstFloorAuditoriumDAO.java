package org.example.hw4.dao;

import org.example.hw4.connection.HibernateSessionManager;
import org.example.hw4.model.FirstFloorAuditorium;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FirstFloorAuditoriumDAO implements SimpleDAO<Integer, FirstFloorAuditorium> {

    private static final SessionFactory SESSION_FACTORY = HibernateSessionManager.getSessionFactory();
    private static final FirstFloorAuditoriumDAO FIRST_FLOOR_AUDITORIUM_DAO = new FirstFloorAuditoriumDAO();


    private FirstFloorAuditoriumDAO() {
    }


    public static FirstFloorAuditoriumDAO getInstance() {return FIRST_FLOOR_AUDITORIUM_DAO;}

    @Override
    public FirstFloorAuditorium save(FirstFloorAuditorium element) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.persist(element);
            session.getTransaction().commit();
        }
        return element;
    }

    @Override
    public Optional<FirstFloorAuditorium> findById(Integer id) {
        Optional<FirstFloorAuditorium> firstFloorAuditorium = Optional.empty();
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            firstFloorAuditorium = Optional.ofNullable(session.get(FirstFloorAuditorium.class, id));
            session.getTransaction().commit();
        }
        return firstFloorAuditorium;
    }

    @Override
    public List<FirstFloorAuditorium> findAll() {
        List<FirstFloorAuditorium> firstFloorAuditoriumList = new ArrayList<>();
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            firstFloorAuditoriumList = session.createQuery("FROM FirstFloorAuditorium", FirstFloorAuditorium.class)
                    .getResultList();
            session.getTransaction().commit();
        }
        return firstFloorAuditoriumList;
    }

    @Override
    public void update(FirstFloorAuditorium element) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            if (Objects.isNull(session.find(FirstFloorAuditorium.class, element.getRoomNumber()))) {
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
            session.remove(session.get(FirstFloorAuditorium.class, id));
            session.getTransaction().commit();
        }
    }

}
