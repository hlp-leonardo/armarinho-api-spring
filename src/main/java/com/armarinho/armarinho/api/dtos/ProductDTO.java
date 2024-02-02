package com.armarinho.armarinho.api.dtos;

public class ProductDTO {

    private Integer id;
    private String name;
    private double price;
    private ProductTypeDTO productType;
    private ProductColorDTO productColor;
    private ProductSizeDTO productSize;

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

    public ProductTypeDTO getProductType() {
        return productType;
    }

    public void setProductTypeDTO(ProductTypeDTO productType) {
        this.productType = productType;
    }

    public ProductColorDTO getProductColor() {
        return productColor;
    }

    public void setProductColor(ProductColorDTO productColor) {
        this.productColor = productColor;
    }

    public ProductSizeDTO getProductSize() {
        return productSize;
    }

    public void setProductSize(ProductSizeDTO productSize) {
        this.productSize = productSize;
    }
}
