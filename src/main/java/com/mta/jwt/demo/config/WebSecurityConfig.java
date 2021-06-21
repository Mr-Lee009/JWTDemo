package com.mta.jwt.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests().antMatchers("/").permitAll().and().
                authorizeRequests().anyRequest().authenticated().and().httpBasic();


//        // Các trang không yêu cầu login như vậy ai cũng có thể vào
//        // được admin hay user hoặc guest có thể vào các trang
//        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
//
//        // Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
//        // Nếu chưa login, nó sẽ redirect tới trang /login.sau Mình dung hasAnyRole
//        // để cho phép ai được quyền vào
//        // 2  ROLE_USER và ROLEADMIN thì ta lấy từ database ra cái mà mình chèn vô
//        // ở bước 1 (chuẩn bị database)
//
//        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
//        // Trang chỉ dành cho ADMIN
//        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
//        http.authorizeRequests().and().formLogin()
//                .loginProcessingUrl("/j_spring_security_check")
//                .loginPage("/login");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/img/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).
                withUser("admin").
                password("$2a$10$FNTqRLh3kIm/dljLpp5GvuGWFB1QcQRIoN7Fbkt5MZf866p0UMRUO").
                roles("ADMIN");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
