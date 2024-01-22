package com.armarinho.armarinho.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCT_COLORS")
public class ProductColor {
    @Id
    @Column(name = "PRODUCT_COLOR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PRODUCT_COLOR_NAME")
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
