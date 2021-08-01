package com.mta.jwt.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "web/user")
public class UserController {

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public String userIndex() {
//        return "user/index";
//    }
}
