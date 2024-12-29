package org.example.hw4.service;

import org.example.hw4.dao.SecondFloorAuditoriumDAO;
import org.example.hw4.model.SecondFloorAuditorium;

import java.util.List;

public class SecondFloorAuditoriumService implements SimpleService<Integer, SecondFloorAuditorium> {

    private static final SecondFloorAuditoriumService S_F_AUD_SERVICE = new SecondFloorAuditoriumService();
    private static final SecondFloorAuditoriumDAO S_F_AUD_DAO = SecondFloorAuditoriumDAO.getInstance();


    private SecondFloorAuditoriumService() {}


    public static SecondFloorAuditoriumService getInstance() {return S_F_AUD_SERVICE;}

    @Override
    public SecondFloorAuditorium create(SecondFloorAuditorium element) {
        return S_F_AUD_DAO.save(element);
    }

    @Override
    public SecondFloorAuditorium findById(Integer id) {
        return S_F_AUD_DAO.findById(id).orElse(null);
    }

    @Override
    public List<SecondFloorAuditorium> findAll() {
        return S_F_AUD_DAO.findAll();
    }

    @Override
    public void update(SecondFloorAuditorium element) {
        S_F_AUD_DAO.update(element);
    }

    @Override
    public void delete(Integer id) {
        S_F_AUD_DAO.delete(id);
    }

}
