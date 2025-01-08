package org.example.hw5.dao;

import org.example.hw5.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("teacherDAO")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class TeacherDAO implements SimpleDAO<Integer, Teacher>{


    private final SessionFactory SESSION_FACTORY;


    @Autowired
    private TeacherDAO(SessionFactory SESSION_FACTORY){
        this.SESSION_FACTORY = SESSION_FACTORY;
    }


    @Override
    public Teacher save(Teacher element) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.persist(element);
        return element;
    }

    @Override
    public Optional<Teacher> findById(Integer id) {

        Session session = SESSION_FACTORY.getCurrentSession();

        return session.createQuery(
                """
                   SELECT t FROM Teacher t
                       JOIN FETCH t.students
                       WHERE t.teacherId =: teachId
                   """, Teacher.class
        )
                .setParameter("teachId", id)
                .uniqueResultOptional();

    }

    @Override
    public List<Teacher> findAll() {
        Session session = SESSION_FACTORY.getCurrentSession();
        return session.createQuery("FROM Teacher t JOIN FETCH t.students", Teacher.class)
                .getResultList();
    }

    @Override
    public void update(Teacher element) {

        Session session = SESSION_FACTORY.getCurrentSession();

        if (Objects.isNull(session.find(Teacher.class, element.getTeacherId()))){
            session.persist(element);
        } else {
            session.merge(element);
        }

    }

    @Override
    public void delete(Integer id) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.remove(session.get(Teacher.class, id));
    }

}