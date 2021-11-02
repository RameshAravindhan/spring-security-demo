package com.springdemo.security;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@ComponentScan(basePackages = "com.springdemo.security")
@EnableWebMvc
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {

    // set up variable to hold properties
    @Autowired
    private Environment environment;

    private Logger logger = Logger.getLogger(getClass().getName());

    // set up bean for view resolver
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();

        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }


    // set up bean for Security Datasource

    @Bean
    public DataSource getConnectionDatasource() {

        //Create connection pool

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        //set jdbc driver class

        try {
            comboPooledDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }


        //log the connection props

        logger.info(">>> jdbc.url=" + environment.getProperty("jdbc.url"));
        logger.info(">>> jdbc.user=" + environment.getProperty("jdbc.user"));

        //set DB connection props

        comboPooledDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        comboPooledDataSource.setUser(environment.getProperty("jdbc.user"));
        comboPooledDataSource.setPassword(environment.getProperty("jdbc.password"));

        // set connection pool props

        comboPooledDataSource.setInitialPoolSize(getproperty("connection.pool.initialPoolSize"));
        comboPooledDataSource.setMinPoolSize(getproperty("connection.pool.minPoolSize"));
        comboPooledDataSource.setMaxPoolSize(getproperty("connection.pool.maxPoolSize"));
        comboPooledDataSource.setMaxIdleTime(getproperty("connection.pool.maxIdleTime"));
        return comboPooledDataSource;
    }

    private int getproperty(String propName) {

        String prop = environment.getProperty(propName);
        int p = Integer.parseInt(prop);
        return p;
    }
}