package com.mta.jwt.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ApiKeyRequestFilter extends GenericFilter {

    private static final Logger LOG = LoggerFactory.getLogger(ApiKeyRequestFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();

        if(path.startsWith("/api") == false){
            chain.doFilter(request, response);
            return;
        }

        String key = req.getHeader("api-key") == null ? "" : req.getHeader("api-key");
        LOG.info("Trying key: " + key);
        List<String> apiKeys = Arrays.asList(
                "ducla",
                "anhduc2781997",
                "2781997",
                "xin chao anh em"
        );
        if(apiKeys.contains(key)){
            chain.doFilter(request, response);
        }
        else {
            HttpServletResponse resp = (HttpServletResponse) response;
            String error = "Invalid API KEY";
            resp.reset();
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentLength(error .length());
            response.getWriter().write(error);
        }
    }
}
