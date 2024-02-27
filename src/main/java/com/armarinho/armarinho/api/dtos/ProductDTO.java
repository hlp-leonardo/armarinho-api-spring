package com.armarinho.armarinho.api.dtos;

public class ProductDTO {

    private Integer id;
    private String name;
    private double price;
    private ProductTypeDTO productTypeDTO;
    private ProductColorDTO productColorDTO;
    private ProductSizeDTO productSizeDTO;

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

    public ProductTypeDTO getProductTypeDTO() {
        return productTypeDTO;
    }

    public void setProductTypeDTO(ProductTypeDTO productType) {
        this.productTypeDTO = productType;
    }

    public ProductColorDTO getProductColorDTO() {
        return productColorDTO;
    }

    public void setProductColorDTO(ProductColorDTO productColorDTO) {
        this.productColorDTO = productColorDTO;
    }

    public ProductSizeDTO getProductSizeDTO() {
        return productSizeDTO;
    }

    public void setProductSizeDTO(ProductSizeDTO productSizeDTO) {
        this.productSizeDTO = productSizeDTO;
    }
}
