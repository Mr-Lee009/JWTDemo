package com.mta.jwt.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan("com.mta.jwt.demo.security")
//@EnableJpaRepositories("com.mta.jwt.demo.security")
//@EntityScan("com.mta.jwt.demo.entity.acl.model")
public class MtaSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(MtaSecurityApplication.class, args);
    }

}
