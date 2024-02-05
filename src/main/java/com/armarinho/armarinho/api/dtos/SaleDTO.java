package com.armarinho.armarinho.api.dtos;

import java.time.Instant;
import java.util.Date;

public class SaleDTO {
    Integer id;
    Instant date;
    ProductDTO product;

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

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
