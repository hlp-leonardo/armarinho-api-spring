package com.armarinho.armarinho.api.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PRODUCT_SIZES")
public class ProductSize {
    @Id
    @Column(name = "PRODUCT_SIZE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PRODUCT_SIZE_NAME")
    private String name;

    @OneToMany(mappedBy = "productSize")
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
