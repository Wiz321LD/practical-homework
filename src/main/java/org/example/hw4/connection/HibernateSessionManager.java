package org.example.hw4.connection;

import org.example.hw4.util.PropertiesUtil;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionManager {

    private static final Configuration configuration = new Configuration();
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";
    private static final String URL = "db.connection_url";
    private static final String DRIVER = "db.driver_class";
    private static final String DIALECT = "db.hibernate_dialect";


    static{
        loadConfiguration();
    }


    private HibernateSessionManager() {}


    private static void loadConfiguration() {
        configuration.setProperty("hibernate.connection.username", PropertiesUtil.getProperty(USERNAME));
        configuration.setProperty("hibernate.connection.password", PropertiesUtil.getProperty(PASSWORD));
        configuration.setProperty("hibernate.connection.url", PropertiesUtil.getProperty(URL));
        configuration.setProperty("hibernate.connection.driver_class", PropertiesUtil.getProperty(DRIVER));
        configuration.setProperty("hibernate.dialect", PropertiesUtil.getProperty(DIALECT));
        configuration.configure();
    }

    public static SessionFactory getSessionFactory() {
        return configuration.buildSessionFactory();
    }

}
