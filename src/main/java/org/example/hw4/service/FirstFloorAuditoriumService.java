package org.example.hw4.service;

import org.example.hw4.dao.FirstFloorAuditoriumDAO;
import org.example.hw4.model.FirstFloorAuditorium;

import java.util.List;

public class FirstFloorAuditoriumService implements SimpleService<Integer, FirstFloorAuditorium> {

    private static final FirstFloorAuditoriumService F_F_AUD_SERVICE_INSTANCE = new FirstFloorAuditoriumService();
    private static final FirstFloorAuditoriumDAO F_F_AUD_DAO = FirstFloorAuditoriumDAO.getInstance();


    private FirstFloorAuditoriumService() {}


    public static FirstFloorAuditoriumService getInstance() {return F_F_AUD_SERVICE_INSTANCE; }

    @Override
    public FirstFloorAuditorium create(FirstFloorAuditorium element) {
        return F_F_AUD_DAO.save(element);
    }

    @Override
    public FirstFloorAuditorium findById(Integer id) {
        return F_F_AUD_DAO.findById(id).orElse(null);
    }

    @Override
    public List<FirstFloorAuditorium> findAll() {
        return F_F_AUD_DAO.findAll();
    }

    @Override
    public void update(FirstFloorAuditorium element) {
        F_F_AUD_DAO.update(element);
    }

    @Override
    public void delete(Integer id) {
        F_F_AUD_DAO.delete(id);
    }

}
