package org.example.hw6.service;

import org.example.hw6.model.FirstFloorAuditorium;
import org.example.hw6.repository.FirstFloorAuditoriumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("firstFloorAuditoriumService")
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class FirstFloorAuditoriumService implements SimpleService<Integer, FirstFloorAuditorium> {


    private final FirstFloorAuditoriumRepository F_F_AUD_REPO;


    @Autowired
    public FirstFloorAuditoriumService(FirstFloorAuditoriumRepository fFAudRepo) {
        F_F_AUD_REPO = fFAudRepo;
    }


    @Override
    public FirstFloorAuditorium create(FirstFloorAuditorium element) {
        return F_F_AUD_REPO.save(element);
    }

    @Transactional(readOnly = true)
    @Override
    public FirstFloorAuditorium findById(Integer id) {
        return F_F_AUD_REPO.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FirstFloorAuditorium> findAll() {
        return F_F_AUD_REPO.findAll();
    }

    @Override
    public void update(FirstFloorAuditorium element) {
        F_F_AUD_REPO.save(element);
    }

    @Override
    public void delete(Integer id) {
        F_F_AUD_REPO.deleteById(id);
    }

}