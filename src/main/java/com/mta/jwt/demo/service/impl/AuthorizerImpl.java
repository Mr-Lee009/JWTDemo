package com.mta.jwt.demo.service.impl;

import com.mta.jwt.demo.service.IAuthorizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ResolvableType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;

@Service("iAuthorizer")
public class AuthorizerImpl implements IAuthorizer {
    private final Logger logger = (Logger) LoggerFactory.getLogger(AuthorizerImpl.class);

    @Override
    public boolean authorize(Authentication authentication, String action, Object callerObj) {
        String securedPath = extractSecuredPath(callerObj);
        if (securedPath == null || "".equals(securedPath.trim())) {//login, logout
            return true;
        }
        String menuCode = securedPath.substring(1);//Bỏ dấu "/" ở đầu Path
        boolean isAllow = false;
        try {
            UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) authentication;
            if (user == null) {
                return isAllow;
            }
            String userId = (String) user.getPrincipal();
            if (userId == null || "".equals(userId.trim())) {
                return isAllow;
            }
            //Truy vấn vào CSDL theo userId + menuCode + action
            //Nếu có quyền thì
            {
                isAllow = true;
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
            throw e;
        }
        return isAllow;
    }

    private String extractSecuredPath(Object callerObj) {
        Class<?> clazz = ResolvableType.forClass(callerObj.getClass()).getRawClass();
        Optional<Annotation> annotation = Arrays.asList(clazz.getAnnotations()).stream().filter((ann) -> {
            return ann instanceof RequestMapping;
        }).findFirst();
        logger.debug("FOUND CALLER CLASS: {}", ResolvableType.forClass(callerObj.getClass()).getType().getTypeName());
        if (annotation.isPresent()) {
            return ((RequestMapping) annotation.get()).value()[0];
        }
        return null;
    }
}
