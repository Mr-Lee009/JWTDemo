package com.mta.jwt.demo.controller;

import com.mta.jwt.demo.dao.UserDAO;
import com.mta.jwt.demo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    Environment environment;

    //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String indexAdmin() {
        return "admin";
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails != null) {
            System.out.printf(userDetails.toString() + " " + userDetails.getAuthorities().toString());
        }
        request.setAttribute("message", userDetails.getUsername());
        request.setAttribute("rule", userDetails.getAuthorities().toString());
        return "homePage";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        request.setAttribute("login","TRANG LOGIN");
        request.setAttribute("msg",environment.getProperty("msg.loginPage",null,null));
        return "login";
    }
}
