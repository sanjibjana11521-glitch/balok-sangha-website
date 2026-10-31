package com.baloksangha.website.config;

import java.util.Properties;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.baloksangha.website.entity.Member;
import com.baloksangha.website.entity.VisitCounter;

@org.springframework.context.annotation.Configuration
public class HibernateConfig {

    @Bean(destroyMethod = "close")
    public SessionFactory sessionFactory(DataSource dataSource, Environment environment) {
        Properties properties = new Properties();
        properties.put("hibernate.connection.datasource", dataSource);
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.format_sql", "true");

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Member.class);
        configuration.addAnnotatedClass(VisitCounter.class);
        configuration.setProperties(properties);
        return configuration.buildSessionFactory();
    }
}
