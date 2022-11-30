package com.mta.jwt.demo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.mta.jwt.demo.repo")
@PropertySource("classpath:application.properties")
//@EntityScan(basePackages={ "com.mta.jwt.demo.entity.acl" })
public class JPAPersistenceConfig {
}
