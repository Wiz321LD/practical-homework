package org.example.hw5.dao;

import org.example.hw5.model.SecondFloorAuditorium;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component("secondFloorAuditoriumDAO")
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class SecondFloorAuditoriumDAO implements SimpleDAO<Integer, SecondFloorAuditorium> {


    private final SessionFactory SESSION_FACTORY;


    @Autowired
    private SecondFloorAuditoriumDAO(SessionFactory SESSION_FACTORY) {
        this.SESSION_FACTORY = SESSION_FACTORY;
    }


    @Override
    public SecondFloorAuditorium save(SecondFloorAuditorium element) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.persist(element);
        return element;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<SecondFloorAuditorium> findById(Integer id) {
        Session session = SESSION_FACTORY.getCurrentSession();
        return Optional.ofNullable(session.get(SecondFloorAuditorium.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<SecondFloorAuditorium> findAll() {
        Session session = SESSION_FACTORY.getCurrentSession();
        return session.createQuery("FROM SecondFloorAuditorium", SecondFloorAuditorium.class)
                .getResultList();
    }

    @Override
    public void update(SecondFloorAuditorium element) {

        Session session = SESSION_FACTORY.getCurrentSession();

        if (Objects.isNull(session.find(SecondFloorAuditorium.class, element.getRoomNumber()))){
            session.persist(element);
        } else {
            session.merge(element);
        }

    }

    @Override
    public void delete(Integer id) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.remove(session.get(SecondFloorAuditorium.class, id));
    }

}