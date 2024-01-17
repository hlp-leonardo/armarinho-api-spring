package com.armarinho.armarinho.api.dtos;

public class ProductDTO {

    private int id;
    private String name;
    private double price;
    private ProductTypeDTO productTypeDTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public ProductTypeDTO getProductTypeDTO() {
        return productTypeDTO;
    }

    public void setProductTypeDTO(ProductTypeDTO productTypeDTO) {
        this.productTypeDTO = productTypeDTO;
    }
}
