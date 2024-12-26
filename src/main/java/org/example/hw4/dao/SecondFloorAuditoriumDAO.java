package org.example.hw4.dao;

import org.example.hw4.connection.HibernateSessionManager;
import org.example.hw4.model.SecondFloorAuditorium;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SecondFloorAuditoriumDAO implements SimpleDAO<Integer, SecondFloorAuditorium> {

    private static final SessionFactory SESSION_FACTORY = HibernateSessionManager.getSessionFactory();
    private static final SecondFloorAuditoriumDAO SECOND_FLOOR_AUDITORIUM_DAO = new SecondFloorAuditoriumDAO();


    private SecondFloorAuditoriumDAO() {}


    public static SecondFloorAuditoriumDAO getInstance() {return SECOND_FLOOR_AUDITORIUM_DAO;}

    @Override
    public SecondFloorAuditorium save(SecondFloorAuditorium element) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.persist(element);
            session.getTransaction().commit();
        }
        return element;
    }

    @Override
    public Optional<SecondFloorAuditorium> findById(Integer id) {
        Optional<SecondFloorAuditorium> secondFloorAuditorium = Optional.empty();
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            secondFloorAuditorium = Optional.ofNullable(session.get(SecondFloorAuditorium.class, id));
            session.getTransaction().commit();
        }
        return secondFloorAuditorium;
    }

    @Override
    public List<SecondFloorAuditorium> findAll() {
        List<SecondFloorAuditorium> secondFloorAuditoriumList = new ArrayList<>();
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            secondFloorAuditoriumList = session.createQuery("FROM SecondFloorAuditorium", SecondFloorAuditorium.class)
                    .getResultList();
            session.getTransaction().commit();
        }
        return secondFloorAuditoriumList;
    }

    @Override
    public void update(SecondFloorAuditorium element) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            if (Objects.isNull(session.find(SecondFloorAuditorium.class, element.getRoomNumber()))){
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
            session.remove(session.get(SecondFloorAuditorium.class, id));
            session.getTransaction().commit();
        }
    }

}
