package org.example.hw5.service;

import org.example.hw5.dao.SimpleDAO;
import org.example.hw5.model.FirstFloorAuditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("firstFloorAuditoriumService")
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class FirstFloorAuditoriumService implements SimpleService<Integer, FirstFloorAuditorium> {


    private final SimpleDAO<Integer, FirstFloorAuditorium> F_F_AUD_DAO;


    @Autowired
    private FirstFloorAuditoriumService(@Qualifier("firstFloorAuditoriumDAO") SimpleDAO<Integer, FirstFloorAuditorium> fFAudDao) {
        F_F_AUD_DAO = fFAudDao;
    }


    @Override
    public FirstFloorAuditorium create(FirstFloorAuditorium element) {
        return F_F_AUD_DAO.save(element);
    }

    @Transactional(readOnly = true)
    @Override
    public FirstFloorAuditorium findById(Integer id) {
        return F_F_AUD_DAO.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
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