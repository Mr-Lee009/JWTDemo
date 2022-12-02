package com.mta.jwt.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/web/home")
public class HomeController {

    @GetMapping(value = "/admin")
    ModelAndView home(){
        System.out.printf("home admin");
        return new ModelAndView("f12.html");
    }
}
