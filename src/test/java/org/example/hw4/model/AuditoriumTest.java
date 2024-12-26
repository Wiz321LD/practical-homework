package org.example.hw4.model;

import org.example.hw4.dao.FirstFloorAuditoriumDAO;
import org.example.hw4.dao.SecondFloorAuditoriumDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuditoriumTest {

    @Test
    public void testPolymorphicQuery() {

        FirstFloorAuditoriumDAO ffDAO = FirstFloorAuditoriumDAO.getInstance();
        SecondFloorAuditoriumDAO sfDAO = SecondFloorAuditoriumDAO.getInstance();

        Auditorium a1 = ffDAO.findById(1).get();
        Auditorium a2 = sfDAO.findById(3).get();
        FirstFloorAuditorium a3 = ffDAO.findById(1).get();
        SecondFloorAuditorium a4 = sfDAO.findById(3).get();

        System.out.println(a1 + "\n" + a3 + "\n\n" + a2 + "\n" + a4);

        assertEquals(a1.getRoomNumber(), a3.getRoomNumber());
        assertEquals(a2.getRoomNumber(), a4.getRoomNumber());

    }

}