package com.mta.jwt.demo.config.multidatasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.mta.jwt.demo.repo.token.repo",
        entityManagerFactoryRef = "tokenEntityManagerFactory",
        transactionManagerRef = "tokenTransactionManager"
)
@PropertySource(value = "classpath:/config/db.properties")
public class TokenDatasourceConfig {

    @Bean
    @ConfigurationProperties("app.datasource.token")
    public DataSourceProperties tokenDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource tokenDataSource() {
        return tokenDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(DriverManagerDataSource.class)
                .build();
    }

    @Bean(name = "tokenEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean tokenEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(tokenDataSource())
                .packages("com.mta.jwt.demo.repo.token.model")
                .build();
    }

    @Bean(name = "tokenTransactionManager")
    public PlatformTransactionManager tokenTransactionManager(
            @Qualifier("tokenEntityManagerFactory")
                    LocalContainerEntityManagerFactoryBean tokenEntityManagerFactory) {
        return new JpaTransactionManager(tokenEntityManagerFactory.getObject());
    }
}
