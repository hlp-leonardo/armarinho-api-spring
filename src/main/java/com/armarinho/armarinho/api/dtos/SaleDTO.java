package com.armarinho.armarinho.api.dtos;

import java.util.Date;

public class SaleDTO {
    int id;
    Date date;
    ProductDTO product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
