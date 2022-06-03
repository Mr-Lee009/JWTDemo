package com.mta.jwt.demo.controller;

import com.mta.jwt.demo.dto.ProductDTO;
import com.mta.jwt.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;


    @RequestMapping(value = "/createData", method = RequestMethod.GET)
    String createData() {
        productService.createData();
        return "Create Data OK!";
    }

    // get all
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    List<ProductDTO> getAll() {
        return productService.getAll();
    }

    // comment searchByName
    @RequestMapping(value = "/searchByName/{name}", method = RequestMethod.GET)
    List<ProductDTO> getAll(@PathVariable("name") String name) {
        return productService.searchByName(name);
    }

    //sortByPrice
    @RequestMapping(value = "/sortByPrice", method = RequestMethod.GET)
    List<ProductDTO> sortByPrice() {
        return productService.sortByPrice();
    }

    // add
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    String add(@RequestBody ProductDTO item) {
        try {
            if (productService.add(item)) {
                return "add success!";
            } else {
                return "add error!";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

//    public static void main(String[] args) {
//        // immutable (ko the thay doi)
//        String str1 = new String("ducla");
//        String str2 = str1;
//        str1 = str1.concat("-1997");
//        System.out.println("str 1 = " + str1);
//        System.out.println("str 2 = " + str2);
//        //mutable (nguoc lai co the thay doi)
//        StringBuilder StrB1 = new StringBuilder("ducla2");
//        StringBuilder StrB2 = StrB1;
//        StrB1.append("-1998");
//        System.out.println("c 1 = " + StrB1);
//        System.out.println("strB 2 = " + StrB2);
//
//    }
}
