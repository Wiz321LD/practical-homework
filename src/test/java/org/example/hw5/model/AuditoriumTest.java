package org.example.hw5.model;

import org.example.hw5.config.ApplicationConfiguration;
import org.example.hw5.dao.SimpleDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(ApplicationConfiguration.class)
class AuditoriumTest {


    private final SimpleDAO<Integer, FirstFloorAuditorium> F_F_AUD_DAO;
    private final SimpleDAO<Integer, SecondFloorAuditorium> S_F_AUD_DAO;


    @Autowired
    AuditoriumTest(@Qualifier("firstFloorAuditoriumDAO") SimpleDAO<Integer, FirstFloorAuditorium> fFAudDao,
                   @Qualifier("secondFloorAuditoriumDAO") SimpleDAO<Integer, SecondFloorAuditorium> sFAudDao) {
        F_F_AUD_DAO = fFAudDao;
        S_F_AUD_DAO = sFAudDao;
    }


    @Test
    public void testCreatingAuditorium() {
        F_F_AUD_DAO.save(new FirstFloorAuditorium(121, "first",
                new UniversityGroup(3, List.of()), true));

        F_F_AUD_DAO.save(new FirstFloorAuditorium(132, "first",
                new UniversityGroup(2, List.of()), false));

        S_F_AUD_DAO.save(new SecondFloorAuditorium(234, "second",
                new UniversityGroup(4, List.of()), "Math"));

        S_F_AUD_DAO.save(new SecondFloorAuditorium(267, "second",
                new UniversityGroup(1, List.of()), "Arts"));
    }

    @Test
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void testPolymorphicQuery() {

        Auditorium a1 = F_F_AUD_DAO.findById(121).get();
        Auditorium a2 = S_F_AUD_DAO.findById(234).get();
        FirstFloorAuditorium a3 = F_F_AUD_DAO.findById(121).get();
        SecondFloorAuditorium a4 = S_F_AUD_DAO.findById(234).get();

        System.out.println(a1 + "\n" + a3 + "\n\n" + a2 + "\n" + a4);

        assertEquals(a1.getRoomNumber(), a3.getRoomNumber());
        assertEquals(a2.getRoomNumber(), a4.getRoomNumber());

    }

}