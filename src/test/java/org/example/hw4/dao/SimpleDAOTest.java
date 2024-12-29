package org.example.hw4.dao;

import org.example.hw4.model.FirstFloorAuditorium;
import org.example.hw4.model.UniversityGroup;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SimpleDAOTest {

    private static final SimpleDAO<Integer, FirstFloorAuditorium> DAO = FirstFloorAuditoriumDAO.getInstance();


    @Test
    @Order(1)
    public void testSaveDAO(){
        DAO.save(new FirstFloorAuditorium(
                12, "first", new UniversityGroup(10, List.of()), true));
        DAO.save(new FirstFloorAuditorium(
                13, "first", new UniversityGroup(9, List.of()), false));
    }

    @Test
    @Order(2)
    public void testFindByIdDAO(){
        FirstFloorAuditorium auditorium = DAO.findById(12).get();
        System.out.println(auditorium);
        assertEquals(12, auditorium.getRoomNumber());
    }

    @Test
    @Order(3)
    public void testFindAllDAO(){
        List<FirstFloorAuditorium> auditoriums = DAO.findAll();
        System.out.println(auditoriums);
        assertFalse(auditoriums.isEmpty());
    }

    @Test
    @Order(4)
    public void testUpdateDAO(){
        FirstFloorAuditorium auditorium = new FirstFloorAuditorium(
                12, "first", new UniversityGroup(8, List.of()), false);
        DAO.update(auditorium);
        assertEquals(auditorium.isStorage_presence(), DAO.findById(12).get().isStorage_presence());
    }

    @Test
    @Order(5)
    public void testDeleteDAO(){
        DAO.delete(12);
        DAO.delete(13);
        assertFalse(DAO.findById(12).isPresent());
    }

}