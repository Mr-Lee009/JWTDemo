package com.mta.jwt.demo.config.multidatasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages = "com.mta.jwt.demo.payment.repo",
        entityManagerFactoryRef = "paymentEntityManagerFactory",
        transactionManagerRef = "paymentTransactionManager"
)
@PropertySource(value = "classpath:/config/db.properties")
public class PaymentDatasourceConfig {

    @Bean
    @ConfigurationProperties("app.datasource.payment")
    @Primary
    public DataSourceProperties paymentDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource paymentDataSource() {
        return paymentDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(DriverManagerDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "paymentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean paymentEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(paymentDataSource())
                .packages("com.mta.jwt.demo.payment.repo.model")
                .build();
    }

    @Primary
    @Bean(name = "paymentTransactionManager")
    public PlatformTransactionManager paymentTransactionManager(
            @Qualifier("paymentEntityManagerFactory")
                    LocalContainerEntityManagerFactoryBean paymentEntityManagerFactory) {
        return new JpaTransactionManager(paymentEntityManagerFactory.getObject());
    }
}
