package org.example.hw4.service;

import org.example.hw4.dao.UniversityGroupDAO;
import org.example.hw4.model.UniversityGroup;

import java.util.List;

public class UniversityGroupService implements SimpleService<Integer, UniversityGroup> {

    private static final UniversityGroupDAO GROUP_DAO = UniversityGroupDAO.getInstance();
    private static final UniversityGroupService GROUP_SERVICE = new UniversityGroupService();


    private UniversityGroupService(){}


    public static UniversityGroupService getInstance(){
        return GROUP_SERVICE;
    }

    @Override
    public UniversityGroup create(UniversityGroup element) {
        return GROUP_DAO.save(element);
    }

    @Override
    public UniversityGroup findById(Integer id) {
        return GROUP_DAO.findById(id).orElse(null);
    }

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
