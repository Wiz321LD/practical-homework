package org.example.hw4.connection;

import org.example.hw4.model.Student;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HibernateSessionManagerTest {

    @Test
    public void testStarSession(){
        try (Session session = HibernateSessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();

            Student student = session.get(Student.class, 1);
            System.out.println(student);

            session.getTransaction().commit();

            assertEquals("Mike", student.getName());
        }
    }

}