package com.mta.jwt.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.*;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@PropertySource("classpath:application.properties")
public class AclMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
    @Autowired
    MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return defaultMethodSecurityExpressionHandler;
    }

    @Bean
    public static MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler(
            @Autowired DataSource dataSource,
            @Autowired CacheManager cacheManager) {

        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        AclPermissionEvaluator permissionEvaluator = new AclPermissionEvaluator(aclService(dataSource, cacheManager));
        expressionHandler.setPermissionEvaluator(permissionEvaluator);
        return expressionHandler;
    }

    @Bean
    public static JdbcMutableAclService aclService(DataSource dataSource, CacheManager cacheManager) {
        return new JdbcMutableAclService(
                dataSource,
                lookupStrategy(dataSource, cacheManager),
                defaultAclCache(cacheManager)
        );
    }


    @Bean
    public static AclAuthorizationStrategy aclAuthorizationStrategy() {
        return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Bean
    public static PermissionGrantingStrategy permissionGrantingStrategy() {
        return new DefaultPermissionGrantingStrategy(
                new ConsoleAuditLogger());
    }

    @Bean(name = {"defaultAclCache", "aclCache"})
    public static AclCache defaultAclCache(org.springframework.cache.CacheManager springCacheManager) {
        org.springframework.cache.Cache cache = springCacheManager.getCache("aclCache");
        return new SpringCacheBasedAclCache(cache,
                permissionGrantingStrategy(),
                aclAuthorizationStrategy());
    }

    @Bean
    public static LookupStrategy lookupStrategy(DataSource dataSource, CacheManager cacheManager) {
        return new BasicLookupStrategy(
                dataSource,
                defaultAclCache(cacheManager),
                aclAuthorizationStrategy(),
                new ConsoleAuditLogger()
        );
    }
}
