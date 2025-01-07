package org.example.hw5.dao;

import org.example.hw5.model.UniversityGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository("universityGroupDAO")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class UniversityGroupDAO implements SimpleDAO<Integer, UniversityGroup>{


    private final SessionFactory SESSION_FACTORY;


    @Autowired
    private UniversityGroupDAO(SessionFactory SESSION_FACTORY){
        this.SESSION_FACTORY = SESSION_FACTORY;
    }


    @Override
    public UniversityGroup save(UniversityGroup element) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.persist(element);
        return element;
    }

    @Override
    public Optional<UniversityGroup> findById(Integer id) {
        Session session = SESSION_FACTORY.getCurrentSession();
        return Optional.ofNullable(session.get(UniversityGroup.class, id));
    }

    @Override
    public List<UniversityGroup> findAll() {
        Session session = SESSION_FACTORY.getCurrentSession();
        return session.createQuery("FROM UniversityGroup", UniversityGroup.class).getResultList();
    }

    @Override
    public void update(UniversityGroup element) {

        Session session = SESSION_FACTORY.getCurrentSession();

        if (Objects.isNull(session.find(UniversityGroup.class, element.getNumber()))){
            session.persist(element);
        } else {
            session.merge(element);
        }

    }

    @Override
    public void delete(Integer id) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.remove(session.get(UniversityGroup.class, id));
    }

}