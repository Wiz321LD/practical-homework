package org.example.hw4.service;

import org.example.hw4.dao.UniversityGroupDAO;
import org.example.hw4.model.UniversityGroup;

import java.util.List;

public class UniversityGroupService {

    private static final UniversityGroupDAO GROUP_DAO = UniversityGroupDAO.getInstance();

    private static final UniversityGroupService SERVICE_INSTANCE = new UniversityGroupService();


    private UniversityGroupService(){}




    public static UniversityGroupService getInstance(){
        return SERVICE_INSTANCE;
    }

}
