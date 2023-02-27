package com.mta.jwt.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mta.jwt.demo.entity.RefreshToken;
import com.mta.jwt.demo.entity.User;
import com.mta.jwt.demo.repository.RefreshTokenRepository;
import com.mta.jwt.demo.repository.UserRepository;
import com.mta.jwt.demo.repository.specifications.SearchCriteria;
import com.mta.jwt.demo.repository.specifications.SearchOperation;
import com.mta.jwt.demo.repository.specifications.UserSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    private Logger Log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/all")
    public String allAccess() {
        Log.info("TestController::allAccess");
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Object userAccess(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                             @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
                             @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
                             @RequestParam(name = "username", required = false, defaultValue = "ASC") String username,
                             @RequestParam(name = "email", required = false, defaultValue = "@gmail.com") String email) {
        Log.info("TestController::userAccess");

        String[] fileds = new String[]{"uuid", "password", "email", "username"};

        if (!Arrays.asList(fileds).contains(sortBy)) {
            return "No property " + sortBy + " found for type!";
        }
        Sort sortable = null;
        if ("ASC".equals(sort)) {
            sortable = Sort.by(sortBy).ascending();
        } else {
            sortable = Sort.by(sortBy).ascending();
        }

        UserSpecification specification_username = new UserSpecification(new SearchCriteria("username", SearchOperation.CONTAINS, username));

        UserSpecification specification_email = new UserSpecification(new SearchCriteria("email", SearchOperation.CONTAINS, email));

        return userRepository.findAll(specification_email.and(specification_username), PageRequest.of(page, size, sortable));
    }

    private Specification<User> isPremium(String username, String email) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.like(root.get("username"), "%" + username + "%"),
                    criteriaBuilder.like(root.get("email"), "%" + email + "%")
            );
        };
    }

    @PostMapping(value = "/user/search")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Object search(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", required = false, defaultValue = "username") String sortBy,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestBody List<SearchCriteria> properties) {
        ObjectMapper obj = new ObjectMapper();

        Log.info("TestController::search");
        String[] fields = new String[]{"uuid", "password", "email", "username"};

        if (!Arrays.asList(fields).contains(sortBy)) {
            return "No property " + sortBy + " found for type!";
        }
        Sort sortable = null;
        if ("ASC".equals(sort)) {
            sortable = Sort.by(sortBy).ascending();
        } else {
            sortable = Sort.by(sortBy).ascending();
        }

        try {

            Specification<User> specification = (root, query, criteriaBuilder) -> {
               List<Predicate> predicates = new ArrayList<>();
               predicates.add(criteriaBuilder.())
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return userRepository.findAll(userSpecification, PageRequest.of(page, size, sortable));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/token")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<RefreshToken> token() {
        Log.info("TestController::token");
        return refreshTokenRepository.findAll();
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        Log.info("TestController::allAccess");
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        Log.info("TestController::adminAccess");
        return "Admin Board.";
    }
}
