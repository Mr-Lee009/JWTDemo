package com.mta.jwt.demo.dto;

public class ProductDTO {
    Long ID;
    String name;
    String color;
    Long price;
    String size;

    public ProductDTO(Long ID, String name, String color, Long price, String size) {
        this.ID = ID;
        this.name = name;
        this.color = color;
        this.price = price;
        this.size = size;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
