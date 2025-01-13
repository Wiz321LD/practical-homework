package org.example.hw6.service;

import org.example.hw6.model.UniversityGroup;
import org.example.hw6.repository.UniversityGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("universityGroupService")
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class UniversityGroupService implements SimpleService<Integer, UniversityGroup> {


    private final UniversityGroupRepository GROUP_REPO;


    @Autowired
    public UniversityGroupService(UniversityGroupRepository groupRepo){
        GROUP_REPO = groupRepo;
    }


    @Override
    public UniversityGroup create(UniversityGroup element) {
        return GROUP_REPO.save(element);
    }

    @Transactional(readOnly = true)
    @Override
    public UniversityGroup findById(Integer id) {
        return GROUP_REPO.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UniversityGroup> findAll() {
        return GROUP_REPO.findAll();
    }

    @Override
    public void update(UniversityGroup element) {
        GROUP_REPO.save(element);
    }

    @Override
    public void delete(Integer id) {
        GROUP_REPO.deleteById(id);
    }

}