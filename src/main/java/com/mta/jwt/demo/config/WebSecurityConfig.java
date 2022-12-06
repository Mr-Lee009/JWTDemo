package com.mta.jwt.demo.config;

import com.mta.jwt.demo.security.jwt.AuthEntryPointJwt;
import com.mta.jwt.demo.security.jwt.AuthTokenFilter;
import com.mta.jwt.demo.service.UserDetailsService;
import com.mta.jwt.demo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService::loadUserByUsername)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable();

        http.authorizeRequests()
                .antMatchers("/api/auth/**",
                        "/api/payment/**",
                        "/web/home/**"
                ).permitAll();

        // config for swagger
        http.authorizeRequests()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui", "/swagger-resources",
                        "/configuration/security",
                        "/swagger-ui.html", "/webjars/**",
                        "/swagger-resources/configuration/ui", "/swagger-ui.html",
                        "/swagger-resources/configuration/security")
                .permitAll();// this

        http.authorizeRequests()
                    .anyRequest().authenticated()

                .and()
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)

                // this disables session creation on Spring Security
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/web/home/**",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/v2/**"
        );
    }


//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService(){
//        UserDetails adam = User.builder()
//                .username("adam")
//                .password(passwordEncoder().encode("adam123"))
//                .roles("ROLE_ADMIN")
//                .build();
//
//        UserDetails linda = User.builder()
//                .username("linda")
//                .password(passwordEncoder().encode("linda456"))
//                .roles("ROLE_USER")
//                .build();
//        return new InMemoryUserDetailsManager(adam,linda);
//    }


}
