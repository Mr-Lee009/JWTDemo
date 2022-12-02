//package com.mta.jwt.demo.client;
//
//import com.mta.jwt.demo.config.FeignConfiguration;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@FeignClient(value = "paymentClient",
//        url = "http://localhost:8080/api/payment",
//        configuration = FeignConfiguration.class)
//
//public interface PaymentClient {
//    @GetMapping(value = "/say-hello")
//    public String sayHello(@RequestParam("name") String name);
//}
