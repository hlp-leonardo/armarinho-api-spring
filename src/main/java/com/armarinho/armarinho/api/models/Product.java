package com.armarinho.armarinho.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_PRICE")
    private double price;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_TYPE_FK")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_COLOR_FK")
    private ProductColor productColor;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_SIZE_FK")
    private ProductSize productSize;

//    @ManyToMany
//    @JoinTable(name = "SALE_PRODUCTS", joinColumns = @JoinColumn(name = "PRODUCT_FK"), inverseJoinColumns = @JoinColumn(name = "SALE_FK"))
//    private List<Sale> sales;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ProductColor getProductColor() {
        return productColor;
    }

    public void setProductColor(ProductColor productColor) {
        this.productColor = productColor;
    }

    public ProductSize getProductSize() {
        return productSize;
    }

    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }

//    public List<Sale> getSales() {
//        return sales;
//    }

//    public void setSales(List<Sale> sales) {
//        this.sales = sales;
//    }
}
