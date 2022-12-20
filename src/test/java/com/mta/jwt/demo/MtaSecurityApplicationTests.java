package com.mta.jwt.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import com.mta.jwt.demo.service.PaymentController;

@SpringBootTest
class MtaSecurityApplicationTests {

    @Autowired
    @Qualifier(value = "PaymentController")
    private PaymentController controller;
    @Test
    void contextLoads() {
        //controller.DoSomething("2B2C");
        System.out.println("___________________________");
        controller.DoSomething("TTB");
        System.out.println("___________________________");
        //controller.DoSomething("CCCC");
    }

}
