package com.mta.jwt.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    Environment environment;

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/home" ,method = RequestMethod.GET)
    public String home(HttpServletRequest request) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("home","TRANG CHU MOI");
        return "homePage";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("login","TRANG LOGIN");
        request.setAttribute("msg",environment.getProperty("msg.loginPage",null,null));
        return "login";
    }
}
