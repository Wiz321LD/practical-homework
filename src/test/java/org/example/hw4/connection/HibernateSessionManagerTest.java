package org.example.hw4.connection;

import org.example.hw4.model.Student;
import org.example.hw4.model.Teacher;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HibernateSessionManagerTest {

    @Test
    public void testStarSession(){
        try (Session session = HibernateSessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();

            Student student = session.get(Student.class, 1);
            Teacher teacher = session.get(Teacher.class, 3);
            System.out.println(student);
            System.out.println(student.getUniversityGroup());
            System.out.println(teacher);

            session.getTransaction().commit();

            assertEquals("Mike", student.getName());
        }
    }

}