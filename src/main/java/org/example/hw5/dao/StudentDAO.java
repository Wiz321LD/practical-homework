package org.example.hw5.dao;

import org.example.hw5.model.Student;
import org.example.hw5.model.UniversityGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.RootGraph;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository("studentDAO")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class StudentDAO implements SimpleDAO<Integer, Student>, StudentHardInsertDeleteDAO<Integer, Student> {


    private final SessionFactory SESSION_FACTORY;
    private static final int BATCH_SIZE = 500;


    @Autowired
    private StudentDAO(SessionFactory SESSION_FACTORY) {
        this.SESSION_FACTORY = SESSION_FACTORY;
    }


    @Override
    public Student save(Student element) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.persist(element);
        return element;
    }

    @Override
    public Optional<Student> findById(Integer id) {

        Session session = SESSION_FACTORY.getCurrentSession();
        session.beginTransaction();

        return session.createQuery(
                """
                   SELECT s FROM Student s
                       JOIN FETCH s.universityGroup
                       JOIN FETCH s.teachers
                       WHERE s.studentId =:studentId
                   """, Student.class
        )
                .setParameter("studentId", id)
                .uniqueResultOptional();

    }

    @Override
    public List<Student> findAll() {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.beginTransaction();
        Query<Student> selectQuery = session.createQuery(
                """
                  SELECT s FROM Student s
                      JOIN FETCH s.universityGroup
                      JOIN FETCH s.teachers
                      ORDER BY s.studentId
                  """, Student.class
        );

        return selectQuery.getResultList();
    }

    @Override
    public void update(Student element) {

        Session session = SESSION_FACTORY.getCurrentSession();

        if (Objects.isNull(session.find(Student.class, element.getStudentId()))){
            session.persist(element);
        } else {
            session.merge(element);
        }

    }

    @Override
    public void delete(Integer id) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.remove(session.get(Student.class, id));
    }

    @Override
    public Student findStudentAndTeachers(Integer studentId){
        Session session = SESSION_FACTORY.getCurrentSession();
        Student student = session.get(Student.class, studentId);
        System.out.println(student.getUniversityGroup());
        System.out.println(student.getTeachers());
        return student;
    }


    @Override
    public void insert100_000BatchRawsInTable() {

        Session session = SESSION_FACTORY.getCurrentSession();

        for (int i = 0; i < 100_000; i++){
            session.persist(new Student("N" + i, "S" + i, new Date(),
                    new UniversityGroup(5, List.of()), List.of(), i));
            if (i % BATCH_SIZE == 0){
                session.flush();
                session.clear();
            }
        }

    }

    @Override
    public void delete100_000BatchRawsInTable() {

        Session session = SESSION_FACTORY.getCurrentSession();

        for (int i = 11; i <= 100_010; i++){
            session.remove(session.get(Student.class, i));
            if (i % BATCH_SIZE == 0){
                session.flush();
                session.clear();
            }
        }

    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public Student getStudentWhereGradeMoreThan2000() {

        Session session = SESSION_FACTORY.getCurrentSession();

        Query<Student> selectQuery = session.createQuery(
                """
                 SELECT s FROM Student s
                     LEFT OUTER JOIN FETCH s.teachers
                     LEFT OUTER JOIN FETCH s.universityGroup
                     WHERE s.grade > 2000
                     ORDER BY s.grade
                 """, Student.class
        ).setMaxResults(1);

        return selectQuery.getResultList().stream().findFirst().get();

    }

    @Override
    public List<Student> getStudentsWhereGradeMoreThan2000() {

        Session session = SESSION_FACTORY.getCurrentSession();

        Query<Student> selectQuery = session.createQuery(
                """
                  SELECT s FROM Student s
                      LEFT OUTER JOIN FETCH s.universityGroup
                      LEFT OUTER JOIN FETCH s.teachers
                      WHERE s.grade > 2000
                      ORDER BY s.grade
                  """, Student.class
        ).setMaxResults(5);

        return selectQuery.getResultList();

    }

}