package org.example.hw5.service;

import org.example.hw5.dao.SimpleDAO;
import org.example.hw5.model.UniversityGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("universityGroupService")
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class UniversityGroupService implements SimpleService<Integer, UniversityGroup> {


    private final SimpleDAO<Integer, UniversityGroup> GROUP_DAO;


    @Autowired
    private UniversityGroupService(@Qualifier("universityGroupDAO") SimpleDAO<Integer, UniversityGroup> groupDao){
        GROUP_DAO = groupDao;
    }


    @Override
    public UniversityGroup create(UniversityGroup element) {
        return GROUP_DAO.save(element);
    }

    @Transactional(readOnly = true)
    @Override
    public UniversityGroup findById(Integer id) {
        return GROUP_DAO.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UniversityGroup> findAll() {
        return GROUP_DAO.findAll();
    }

    @Override
    public void update(UniversityGroup element) {
        GROUP_DAO.update(element);
    }

    @Override
    public void delete(Integer id) {
        GROUP_DAO.delete(id);
    }

}