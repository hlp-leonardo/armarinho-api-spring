package com.armarinho.armarinho.api.dtos;

import com.armarinho.armarinho.api.models.Product;

import java.util.List;

public class ProductTypeDTO {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
