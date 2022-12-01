package com.mta.jwt.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.mta.jwt.demo")
public class MtaSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(MtaSecurityApplication.class, args);
    }
}
