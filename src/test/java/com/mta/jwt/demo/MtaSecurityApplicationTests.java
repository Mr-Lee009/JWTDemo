package com.mta.jwt.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mta.jwt.demo.entity.User;
import com.mta.jwt.demo.service.UserDetailsService;
import com.mta.jwt.demo.utill.TestData;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
public class MtaSecurityApplicationTests {

    @Autowired
    UserDetailsService detailsService;
    public static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void contextLoads() throws JsonProcessingException {
        UserDetails data = detailsService.loadUserByUsername("BinhThuong3");
        System.out.println(objectMapper.writeValueAsString(data));
        Assertions.assertArrayEquals("BinhThuong3".toCharArray(), data.getUsername().toCharArray());
    }

    @TestData(FilePath = "./", Key = "user")
    public void contextLoads2(User data,String str) throws JsonProcessingException {
        Assertions.assertArrayEquals("BinhThuong3".toCharArray(), data.getUsername().toCharArray());
    }
}
