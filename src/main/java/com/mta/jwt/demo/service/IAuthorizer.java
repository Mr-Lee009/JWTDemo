package com.mta.jwt.demo.service;

import org.springframework.security.core.Authentication;

public interface IAuthorizer {
    boolean authorize(Authentication authentication, String action, Object callerObj);
}
