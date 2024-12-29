package org.example.hw4.dao;

import org.example.hw4.connection.HibernateSessionManager;
import org.example.hw4.model.Student;
import org.example.hw4.model.UniversityGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.RootGraph;
import org.hibernate.query.Query;

import java.util.*;

public class StudentDAO implements SimpleDAO<Integer, Student>{

    private static final SessionFactory SESSION_FACTORY = HibernateSessionManager.getSessionFactory();
    private static final StudentDAO STUDENT_DAO_INSTANCE = new StudentDAO();
    private static final int BATCH_SIZE = 500;


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

            RootGraph<?> entityGraph = session.getEntityGraph("graphOfUniversityGroupAndTeachers");

            Map<String, Object> graphProperties = new HashMap<>();
            graphProperties.put("jakarta.persistence.fetchgraph", entityGraph);

            studentOptional = Optional.ofNullable(session.find(Student.class, id, graphProperties));

            session.getTransaction().commit();
        }
        return studentOptional;
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            studentList = session.createQuery("FROM Student", Student.class)
                    .setHint("jakarta.persistence.fetchgraph", session.getEntityGraph("graphOfUniversityGroupAndTeachers"))
                    .getResultList();

            session.getTransaction().commit();
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

    public Student findStudentAndTeachers(Integer studentId){
        Student student = new Student();
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            student = session.get(Student.class, studentId);
            System.out.println(student.getUniversityGroup());
            System.out.println(student.getTeachers());

            session.getTransaction().commit();
        }
        return student;
    }

    public void insert100_000BatchRawsInTable(){
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            for (int i = 0; i < 100_000; i++){
                session.persist(new Student("N" + i, "S" + i, new Date(),
                        new UniversityGroup(5, List.of()), List.of(), i));
                if (i % BATCH_SIZE == 0){
                    session.flush();
                    session.clear();
                }
            }

            session.getTransaction().commit();
        }
    }

    public void delete100_000BatchRawsInTable(){
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            for (int i = 11; i <= 100_010; i++){
                session.remove(session.get(Student.class, i));
                if (i % BATCH_SIZE == 0){
                    session.flush();
                    session.clear();
                }
            }

            session.getTransaction().commit();
        }
    }

    public Student getStudentWhereGradeMoreThan2000(){
        Student student = new Student();
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            Query<Student> selectQuery = session.createQuery(
                            """
                             SELECT s FROM Student s
                                 LEFT OUTER JOIN FETCH s.teachers
                                 LEFT OUTER JOIN FETCH s.universityGroup
                                 WHERE s.grade > 2000
                                 ORDER BY s.grade
                             """, Student.class
                    ).setMaxResults(1);

            student = selectQuery.getResultList().stream().findFirst().get();

            session.getTransaction().commit();
        }
        return student;
    }

    public List<Student> getStudentsWhereGradeMoreThan2000(){
        List<Student> studentList = new ArrayList<>();
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            Query<Student> selectQuery = session.createQuery(
                    """
                      SELECT s FROM Student s
                          LEFT OUTER JOIN FETCH s.universityGroup
                          LEFT OUTER JOIN FETCH s.teachers
                          WHERE s.grade > 2000
                          ORDER BY s.grade
                      """, Student.class
            ).setMaxResults(5);

            studentList = selectQuery.getResultList();

            session.getTransaction().commit();
        }
        return studentList;
    }

}
