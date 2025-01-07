package org.example.hw5.dao;

import org.example.hw5.model.FirstFloorAuditorium;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository("firstFloorAuditoriumDAO")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class FirstFloorAuditoriumDAO implements SimpleDAO<Integer, FirstFloorAuditorium> {


    private final SessionFactory SESSION_FACTORY;


    @Autowired
    private FirstFloorAuditoriumDAO(SessionFactory SESSION_FACTORY) {
        this.SESSION_FACTORY = SESSION_FACTORY;
    }


    @Override
    public FirstFloorAuditorium save(FirstFloorAuditorium element) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.persist(element);
        return element;
    }

    @Override
    public Optional<FirstFloorAuditorium> findById(Integer id) {
        Session session = SESSION_FACTORY.getCurrentSession();
        return Optional.ofNullable(session.get(FirstFloorAuditorium.class, id));
    }

    @Override
    public List<FirstFloorAuditorium> findAll() {
        Session session = SESSION_FACTORY.getCurrentSession();
        return session.createQuery("FROM FirstFloorAuditorium", FirstFloorAuditorium.class).getResultList();
    }

    @Override
    public void update(FirstFloorAuditorium element) {

        Session session = SESSION_FACTORY.getCurrentSession();

        if (Objects.isNull(session.find(FirstFloorAuditorium.class, element.getRoomNumber()))) {
            session.persist(element);
        } else {
            session.merge(element);
        }

    }

    @Override
    public void delete(Integer id) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.remove(session.get(FirstFloorAuditorium.class, id));
    }

}