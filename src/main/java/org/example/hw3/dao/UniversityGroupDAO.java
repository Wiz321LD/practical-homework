package org.example.hw3.dao;

import org.example.hw3.connection.JdbcConnectionManager;
import org.example.hw3.model.Student;
import org.example.hw3.model.UniversityGroup;

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
        try(
                Connection connection = JdbcConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_NEW_GROUP);
                ){
            statement.setInt(1, universityGroup.getNumber());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    //READ one group
    public UniversityGroup selectUniversityGroupById(int id) {
        UniversityGroup universityGroup = null;
        try(
                Connection connection = JdbcConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_UNIVERSITY_GROUP_BY_ID);){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int number = resultSet.getInt("number");
                Set<Student> students = resultSet.getObject("students", HashSet.class);
                universityGroup = new UniversityGroup(number, students);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return universityGroup;
    }

    //READ all groups
   public List<UniversityGroup> selectAllUniversityGroups() {
       List<UniversityGroup> universityGroups = new ArrayList<>();
       try (
               Connection connection = JdbcConnectionManager.getConnection();
               PreparedStatement statement = connection.prepareStatement(SELECT_ALL_UNIVERSITY_GROUPS);
       ) {
           ResultSet resultSet = statement.executeQuery();
           while (resultSet.next()) {
               int number = resultSet.getInt("number");
               Set<Student> students = resultSet.getObject("students", HashSet.class);
               universityGroups.add(new UniversityGroup(number, students));
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return universityGroups;
   }

    public static UniversityGroupDAO getInstance() {
        return UNIVERSITY_GROUP_DAO_INSTANCE;
    }

}
