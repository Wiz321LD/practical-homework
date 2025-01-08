package org.example.hw5.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:applicationProperties.properties")
@ComponentScan(basePackages = "org.example")
@EnableTransactionManagement
@EnableWebMvc
public class ApplicationConfiguration implements WebMvcConfigurer {


    private final Environment ENVIRONMENT;


    @Autowired
    public ApplicationConfiguration(Environment ENVIRONMENT) {
        this.ENVIRONMENT = ENVIRONMENT;
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(Objects.requireNonNull(ENVIRONMENT.getRequiredProperty("hibernate.connection.driver_class")));
        dataSource.setUrl(ENVIRONMENT.getRequiredProperty("hibernate.connection.url"));
        dataSource.setUsername(ENVIRONMENT.getRequiredProperty("hibernate.connection.username"));
        dataSource.setPassword(ENVIRONMENT.getRequiredProperty("hibernate.connection.password"));

        return dataSource;
    }

    private Properties hibernateProperties(){
        Properties properties = new Properties();

        properties.put("hibernate.dialect", ENVIRONMENT.getProperty("hibernate.dialect"));
        properties.put("show_sql", ENVIRONMENT.getProperty("show_sql"));
        properties.put("format_sql", ENVIRONMENT.getProperty("format_sql"));

        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean  = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("org.example.hw5.model");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());

        return localSessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();

        hibernateTransactionManager.setSessionFactory(sessionFactoryBean().getObject());

        return hibernateTransactionManager;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }

}
