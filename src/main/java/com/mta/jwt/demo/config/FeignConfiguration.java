package com.mta.jwt.demo.config;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import feign.form.ContentType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
//    @Bean
//    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
//        return new BasicAuthRequestInterceptor("Authorization", "ts");
//    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtZW9tZW8yIiwiaWF0IjoxNjY5ODAxMjM0LCJleHAiOjE2Njk4MDE1MzR9.vHKR7XEiEWeDB6dGHYhqbcu0e_aRAsVdTtNSjo3gp8MHJ0ccs4ehZEppzYJ91S_woTrwdm-F1utaEPeNOyKFDQ");
            requestTemplate.header("Accept", "application/json");
            requestTemplate.header("huhu", "ducla");
        };
    }
}
