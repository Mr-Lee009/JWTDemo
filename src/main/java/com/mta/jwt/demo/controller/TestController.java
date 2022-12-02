package com.mta.jwt.demo.controller;

//import com.mta.jwt.demo.client.PaymentClient;
import com.mta.jwt.demo.entity.RefreshToken;
import com.mta.jwt.demo.entity.User;
import com.mta.jwt.demo.repository.RefreshTokenRepository;
import com.mta.jwt.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    //PaymentClient paymentClient;

    @GetMapping("/all")
    public String allAccess() {
        //String name = paymentClient.sayHello(TestController.class.getName());
        //System.out.printf("name" + name);
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<User> userAccess() {
        return userRepository.findAll();
    }

    @GetMapping("/token")
    @PreAuthorize("hasRole('USER') and hasRole('ADMIN')")
    public List<RefreshToken> token() {
        return refreshTokenRepository.findAll();
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
