package com.mta.jwt.demo.config;

import com.mta.jwt.demo.filter.APIKeyAuthFilter;
import com.mta.jwt.demo.security.jwt.AuthEntryPointJwt;
import com.mta.jwt.demo.security.jwt.AuthTokenFilter;
import com.mta.jwt.demo.security.jwt.CustomAuthenticationProvider;
import com.mta.jwt.demo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(
//        // securedEnabled = true,
//        // jsr250Enabled = true,
//        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;
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


    @Value("${yourapp.http.auth-token-header-name}")
    private String principalRequestHeader;
    @Value("${yourapp.http.auth-token}")
    private String principalRequestValue;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        APIKeyAuthFilter filter = new APIKeyAuthFilter(principalRequestHeader);
//        filter.setAuthenticationManager(new AuthenticationManager() {
//            @Override
//            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                String principal = (String) authentication.getPrincipal();
//                if (!principalRequestValue.equals(principal))
//                {
//                    throw new BadCredentialsException("The API key was not found or not the expected value.");
//                }
//                authentication.setAuthenticated(true);
//                return authentication;
//            }
//        });

        http.cors().and().csrf().disable();

        // don't authenticate this particular request
        http.authorizeRequests().antMatchers(
                "/", "/auth"
        ).permitAll();

        //skip for swagger ui
        http.authorizeRequests().antMatchers(
                "/auth/**",
                "/swagger-ui/**",
                "/api/**",
                "/swagger",
                "/swagger/**",
                "/swagger-resources/**",
                "/v2/**",
                "/v3/**"
        ).permitAll();


        // all other requests need to be authenticated
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                // make sure we use stateless session; session won't be used to
                // store user's state.
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //http.addFilter(filter);
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }
}
