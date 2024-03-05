package com.armarinho.armarinho.api.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PRODUCT_TYPES")
public class ProductType {
    @Id
    @Column(name = "PRODUCT_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PRODUCT_TYPE_NAME")
    private String name;

    @OneToMany(mappedBy = "productType")
    private List<Product> products;

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

    public List<Product> getProduct() {
        return products;
    }

    public void setProduct(List<Product> product) {
        this.products = product;
    }
}
