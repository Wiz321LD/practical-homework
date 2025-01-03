package org.example.hw5.service;

import org.example.hw5.dao.SimpleDAO;
import org.example.hw5.model.SecondFloorAuditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("secondFloorAuditoriumService")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SecondFloorAuditoriumService implements SimpleService<Integer, SecondFloorAuditorium> {


    private final SimpleDAO<Integer, SecondFloorAuditorium> S_F_AUD_DAO;


    @Autowired
    private SecondFloorAuditoriumService(@Qualifier("secondFloorAuditoriumDAO") SimpleDAO<Integer, SecondFloorAuditorium> sFAudDao) {
        S_F_AUD_DAO = sFAudDao;
    }


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