package com.armarinho.armarinho.api.dtos;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class SaleDTO {
    Integer id;
    Instant date;
    List<ProductDTO> products;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
