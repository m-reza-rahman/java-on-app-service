package com.azure.samples.todo;

import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;
import java.util.Properties;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.azure.samples.todo")
@EnableJpaRepositories(basePackages = "com.azure.samples.todo")
public class AppConfig implements WebMvcConfigurer {

    private static final String PROPERTY_NAME_DB_DRIVER_CLASS = "org.postgresql.Driver";
    private static final String PROPERTY_NAME_DB_PASSWORD = "POSTGRESQL_DB_PASSWORD";
    private static final String PROPERTY_NAME_DB_URL = "POSTGRESQL_DB_URL";
    private static final String PROPERTY_NAME_DB_USER = "POSTGRESQL_DB_USER";
    private static final String PROPERTY_NAME_DATABASE_ACTION = "jakarta.persistence.schema-generation.database.action";
    private static final String PROPERTY_VALUE_DATABASE_ACTION = "drop-and-create";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_VALUE_HIBERNATE_SHOW_SQL = "true";
    private static final String PROPERTY_NAME_HIBERNATE_TRANSACTION_COORDINATOR_CLASS = "hibernate.transaction.coordinator_class";
    private static final String PROPERTY_VALUE_HIBERNATE_TRANSACTION_COORDINATOR_CLASS = "jdbc";
    private static final String EM_PACKAGE_TO_SCAN = "com.azure.samples.todo";

    @Bean
    public DataSource dataSource(Environment env) {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(PROPERTY_NAME_DB_DRIVER_CLASS);
        dataSourceConfig.setJdbcUrl(env.getRequiredProperty(PROPERTY_NAME_DB_URL));
        dataSourceConfig.setUsername(env.getRequiredProperty(PROPERTY_NAME_DB_USER));
        dataSourceConfig.setPassword(env.getRequiredProperty(PROPERTY_NAME_DB_PASSWORD));

        return new HikariDataSource(dataSourceConfig);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(EM_PACKAGE_TO_SCAN);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_DATABASE_ACTION, PROPERTY_VALUE_DATABASE_ACTION);
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, PROPERTY_VALUE_HIBERNATE_SHOW_SQL);
        properties.put(PROPERTY_NAME_HIBERNATE_TRANSACTION_COORDINATOR_CLASS,
                PROPERTY_VALUE_HIBERNATE_TRANSACTION_COORDINATOR_CLASS);
        em.setJpaProperties(properties);
        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
