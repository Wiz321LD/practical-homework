package org.example.hw4.dao;

import org.example.hw4.connection.HibernateSessionManager;
import org.example.hw4.model.Student;
import org.example.hw4.model.UniversityGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniversityGroupDAO {

    private static final String SELECT_UNIVERSITY_GROUP_BY_ID = "SELECT number FROM university_group WHERE number = ?";
    private static final String SELECT_ALL_UNIVERSITY_GROUPS = "SELECT * FROM university_group";
    private static final String INSERT_NEW_GROUP = "INSERT INTO university_group(number) VALUES (?)";
    private static final UniversityGroupDAO UNIVERSITY_GROUP_DAO_INSTANCE = new UniversityGroupDAO();


    private UniversityGroupDAO(){}


    //CREATE one group
    public void insertUniversityGroup(UniversityGroup universityGroup) {
        //TODO:
    }


    //READ one group
    public UniversityGroup selectUniversityGroupById(int id) {
        //TODO:
        return null;
    }

    //READ all groups
   public List<UniversityGroup> selectAllUniversityGroups() {
       //TODO:
       return null;
   }

    public static UniversityGroupDAO getInstance() {
        return UNIVERSITY_GROUP_DAO_INSTANCE;
    }

}
