package com.mta.jwt.demo.config;

import com.mta.jwt.demo.filter.TransactionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<TransactionFilter> loggingFilter() {
        FilterRegistrationBean<TransactionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TransactionFilter());
        registrationBean.addUrlPatterns("/api/test/*");
        return registrationBean;
    }
}
