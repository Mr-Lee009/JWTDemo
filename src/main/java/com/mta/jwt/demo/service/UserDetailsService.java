package com.mta.jwt.demo.service;

import com.mta.jwt.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserDetailsService {
    public static final int MAX_FAILED_ATTEMPTS = 3;
    public static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours
    //public static final long LOCK_TIME_DURATION = 30 * 1000; // 30s hours

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public void increaseFailedAttempts(User user);
    public void resetFailedAttempts(String email);
    public void lock(User user);
    public boolean unlockWhenTimeExpired(User user);

}
