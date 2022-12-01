package com.mta.jwt.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class paymentController {
    @GetMapping(value = "/api/payment/say-hello")
    public String hello(@RequestParam("name") String name) {
        return "This is paymentController " + name;
    }

//    @GetMapping(value = "/api/payment/say-hello-2")
//    public String token(@RequestHeader("token") String token) {
//        return "This is token " + token;
//    }

}
