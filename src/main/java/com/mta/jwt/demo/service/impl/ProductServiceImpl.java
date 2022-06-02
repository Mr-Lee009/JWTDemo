package com.mta.jwt.demo.service.impl;

import com.mta.jwt.demo.dto.ProductDTO;
import com.mta.jwt.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private List<ProductDTO> lst = new ArrayList<>();

    @Override
    public void createData() {
        this.lst.add(new ProductDTO(1L, "dress", "yellow", 1000L, "M"));
        this.lst.add(new ProductDTO(5L, "shoes2", "black", 5000L, "M"));
        this.lst.add(new ProductDTO(6L, "shoes3", "orange", 6000L, "M"));
        this.lst.add(new ProductDTO(3L, "shoes", "black", 4000L, "M"));
        this.lst.add(new ProductDTO(2L, "T-shirt", "while", 2000L, "M"));
        this.lst.add(new ProductDTO(4L, "shoes1", "pink", 5000L, "M"));
    }

    @Override
    public List<ProductDTO> getAll() {
        return this.lst;
    }

    @Override
    public List<ProductDTO> sortByPrice() {
        this.lst.sort((o1, o2) -> {
            if (o1.getPrice() > o2.getPrice()) {
                return 1;
            } else if (o1.getPrice() < o2.getPrice()) {
                return -1;
            } else {
                return 0;
            }
        });
        return this.lst;
    }

    @Override
    public List<ProductDTO> searchByName(String name) {
        return this.lst.stream().filter(productDTO -> {
            return productDTO.getName().contains(name);
        }).collect(Collectors.toList());
    }

    @Override
    public boolean add(ProductDTO item) {
        return this.lst.add(item);
    }
}
