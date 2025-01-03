package org.example.hw5.dao;

import org.example.hw5.config.ApplicationConfiguration;
import org.example.hw5.model.FirstFloorAuditorium;
import org.example.hw5.model.UniversityGroup;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringJUnitConfig(ApplicationConfiguration.class)
class SimpleDAOTest {


    private final SimpleDAO<Integer, FirstFloorAuditorium> F_F_AUD_DAO;


    @Autowired
    SimpleDAOTest(@Qualifier("firstFloorAuditoriumDAO") SimpleDAO<Integer, FirstFloorAuditorium> fFAudDao) {
        F_F_AUD_DAO = fFAudDao;
    }


    @Test
    @Order(1)
    public void testSaveDAO(){
        F_F_AUD_DAO.save(new FirstFloorAuditorium(
                12, "first", new UniversityGroup(10, List.of()), true));
        F_F_AUD_DAO.save(new FirstFloorAuditorium(
                13, "first", new UniversityGroup(9, List.of()), false));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    @Order(2)
    public void testFindByIdDAO(){
        FirstFloorAuditorium auditorium = F_F_AUD_DAO.findById(12).get();
        System.out.println(auditorium);
        assertEquals(12, auditorium.getRoomNumber());
    }

    @Test
    @Order(3)
    public void testFindAllDAO(){
        List<FirstFloorAuditorium> auditoriums = F_F_AUD_DAO.findAll();
        System.out.println(auditoriums);
        assertFalse(auditoriums.isEmpty());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    @Order(4)
    public void testUpdateDAO(){
        FirstFloorAuditorium auditorium = new FirstFloorAuditorium(
                12, "first", new UniversityGroup(8, List.of()), false);
        F_F_AUD_DAO.update(auditorium);
        assertEquals(auditorium.isStorage_presence(), F_F_AUD_DAO.findById(12).get().isStorage_presence());
    }

    @Test
    @Order(5)
    public void testDeleteDAO(){
        F_F_AUD_DAO.delete(12);
        F_F_AUD_DAO.delete(13);
        assertFalse(F_F_AUD_DAO.findById(12).isPresent());
    }

}