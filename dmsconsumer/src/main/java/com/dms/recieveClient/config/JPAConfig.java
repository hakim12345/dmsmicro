package com.dms.recieveClient.config;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.dms.recieveClient.model"})
@EnableJpaRepositories("com.dms.recieveClient.repository")
public class JPAConfig {

    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factory.setPackagesToScan("com.dms.recieveClient.model");
        factory.setDataSource(dataSource);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(Boolean.FALSE);
        vendorAdapter.setShowSql(Boolean.TRUE);
        vendorAdapter.setDatabasePlatform(env.getRequiredProperty("hibernate.dialect"));
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaPropertyMap(hibernateJpaProperties());
        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        factory.afterPropertiesSet();
        return factory;
    }

    private Map<String, ?> hibernateJpaProperties() {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("hibernate.default_schema", env.getRequiredProperty("db.schema"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "false");
        properties.put("hibernate.cache.provider_class", "org.hibernate.cache.EhCacheProvider");
        properties.put("hibernate.cache.use_second_level_cache", "true");
        properties.put("hibernate.enable_lazy_load_no_trans", "true");
        return properties;
    }

    // Declare a transaction manager
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }}
