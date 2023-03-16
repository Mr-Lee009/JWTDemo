package com.mta.jwt.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MtaSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(MtaSecurityApplication.class, args);
    }

}
