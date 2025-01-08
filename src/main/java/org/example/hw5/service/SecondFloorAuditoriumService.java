package org.example.hw5.service;

import org.example.hw5.model.SecondFloorAuditorium;
import org.example.hw5.repository.SecondFloorAuditoriumRepository;
import org.example.hw5.repository.SimpleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("secondFloorAuditoriumService")
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class SecondFloorAuditoriumService implements SimpleService<Integer, SecondFloorAuditorium> {


    private final SecondFloorAuditoriumRepository S_F_AUD_REPO;


    @Autowired
    public SecondFloorAuditoriumService(SecondFloorAuditoriumRepository sFAudRepo) {
        S_F_AUD_REPO = sFAudRepo;
    }


    @Override
    public SecondFloorAuditorium create(SecondFloorAuditorium element) {
        return S_F_AUD_REPO.save(element);
    }

    @Transactional(readOnly = true)
    @Override
    public SecondFloorAuditorium findById(Integer id) {
        return S_F_AUD_REPO.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<SecondFloorAuditorium> findAll() {
        return S_F_AUD_REPO.findAll();
    }

    @Override
    public void update(SecondFloorAuditorium element) {
        S_F_AUD_REPO.save(element);
    }

    @Override
    public void delete(Integer id) {
        S_F_AUD_REPO.deleteById(id);
    }

}