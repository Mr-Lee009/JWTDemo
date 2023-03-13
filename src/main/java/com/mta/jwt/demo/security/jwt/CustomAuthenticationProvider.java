package com.mta.jwt.demo.security.jwt;

import com.mta.jwt.demo.entity.User;
import com.mta.jwt.demo.repository.UserRepository;
import com.mta.jwt.demo.service.UserDetailsService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();


        User user = userRepository.findByUsername(username).get();
        if (user == null) {
            throw new LockedException("not found user!");
        }
        if(!encrypt.matches(pwd,user.getPassword())){
            if (user.isEnabled() && user.getAccountNonLocked()) {
                if (user.getFailedAttempt() < userDetailsService.MAX_FAILED_ATTEMPTS - 1) {
                    userDetailsService.increaseFailedAttempts(user);
                } else {
                    userDetailsService.lock(user);
                    throw new LockedException("Your account has been locked due to 3 failed attempts."
                            + " It will be unlocked after 24 hours.");
                }
            } else if (!user.getAccountNonLocked()) {
                if (userDetailsService.unlockWhenTimeExpired(user)) {
                    throw new LockedException("Your account has been unlocked. Please try to login again.");
                }
            }
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(username, pwd, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
