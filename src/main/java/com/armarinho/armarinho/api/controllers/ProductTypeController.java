package com.armarinho.armarinho.api.controllers;

import com.armarinho.armarinho.api.models.ProductType;
import com.armarinho.armarinho.api.services.ProductTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/product-types")
@RestController
public class ProductTypeController {
    private final ProductTypeService service;
    public ProductTypeController(ProductTypeService service) {
        this.service = service;
    }

    @PostMapping
    public ProductType create(ProductType productType) {
        productType = service.create(productType);
        return productType;
    }

    @GetMapping
    public List<ProductType> getAll() {
        List<ProductType> allProductTypes = service.getAll();
        return allProductTypes;
    }
}
