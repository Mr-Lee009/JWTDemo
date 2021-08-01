package com.mta.jwt.demo.config;

import com.mta.jwt.demo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        // Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // Các trang không yêu cầu login như vậy ai cũng có thể vào
        // được admin hay user hoặc guest có thể vào các trang
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();

        // Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
        // Nếu chưa login, nó sẽ redirect tới trang /login.sau Mình dung hasAnyRole
        // để cho phép ai được quyền vào
        // 2  ROLE_USER và ROLEADMIN thì ta lấy từ database ra cái mà mình chèn vô
        // ở bước 1 (chuẩn bị database)

        //http.authorizeRequests().antMatchers("/home").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
        // Trang chỉ dành cho ADMIN
        //http.authorizeRequests().antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN','ROLE_SA')");
        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        // Cấu hình cho Logout Page.

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        //http.httpBasic();
        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/login_web")
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                // cau hinh logout
                .and().logout()
                .logoutUrl("/logout_web")
                .logoutSuccessUrl("/logout");

        // Cấu hình Remember Me. Ở form login bước 3, ta có 1 nút remember me.
        // Nếu người dùng tick vào đó ta sẽ dùng cookie lưu lại trong 24h
        http.authorizeRequests().and().rememberMe()
                .tokenRepository(this.persistentTokenRepository())
                .tokenValiditySeconds(15*60);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl(); // Ta lưu tạm remember me trong memory (RAM). Nếu cần mình có thể lưu trong database
        return memory;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder());
//                withUser("admin").
//                password("$2a$10$FNTqRLh3kIm/dljLpp5GvuGWFB1QcQRIoN7Fbkt5MZf866p0UMRUO").
//                roles("ADMIN");
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
