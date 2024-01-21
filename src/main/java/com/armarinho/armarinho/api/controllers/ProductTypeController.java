package com.armarinho.armarinho.api.controllers;

import com.armarinho.armarinho.api.models.ProductType;
import com.armarinho.armarinho.api.services.ProductTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/product-types")
@RestController
public class ProductTypeController {
    private final ProductTypeService service;
    public ProductTypeController(ProductTypeService service) {
        this.service = service;
    }

    @PostMapping
    public ProductType create(@RequestBody ProductType productType) {
        productType = service.create(productType);
        return productType;
    }

    @GetMapping
    public List<ProductType> getAll() {
        List<ProductType> allProductTypes = service.getAll();
        return allProductTypes;
    }

    @GetMapping("/{id}")
    public Optional<ProductType> getOne(@PathVariable("id") int id) {
        Optional<ProductType> productType = service.getOne(id);
        return productType;
    }

    @PutMapping("/{id}")
    public ProductType update(@PathVariable("id") int id, @RequestBody ProductType productType) {
        productType = service.update(id, productType);
        return productType;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
