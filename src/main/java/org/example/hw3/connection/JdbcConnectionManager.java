package org.example.hw3.connection;

import org.example.hw3.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionManager {

    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";
    private static final String URL = "db.connection_url";


//    static{
//        loadDriver();
//    }


    private JdbcConnectionManager() {}


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.getProperty(URL),
                    PropertiesUtil.getProperty(USERNAME),
                    PropertiesUtil.getProperty(PASSWORD)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    private static void loadDriver(){
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
