package org.example.hw3.service;

import org.example.hw3.dao.UniversityGroupDAO;
import org.example.hw3.model.UniversityGroup;

import java.util.List;

public class UniversityGroupService {

    private static final UniversityGroupDAO GROUP_DAO = UniversityGroupDAO.getInstance();

    private static final UniversityGroupService SERVICE_INSTANCE = new UniversityGroupService();


    private UniversityGroupService(){}


    //CREATE
    public void createNewUniversityGroup(UniversityGroup group) {
        GROUP_DAO.insertUniversityGroup(group);
    }

    //READ
    public UniversityGroup getOneUniversityGroupById(int id) {
        return GROUP_DAO.selectUniversityGroupById(id);
    }

    //READ
    public List<UniversityGroup> getAllUniversityGroups() {
        return GROUP_DAO.selectAllUniversityGroups();
    }

    public static UniversityGroupService getInstance(){
        return SERVICE_INSTANCE;
    }

}
