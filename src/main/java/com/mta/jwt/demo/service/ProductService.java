package com.mta.jwt.demo.service;

import com.mta.jwt.demo.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    void createData();

    List<ProductDTO> getAll();

    List<ProductDTO> sortByPrice();

    List<ProductDTO> searchByName(String name);

    boolean add(ProductDTO item);
}
