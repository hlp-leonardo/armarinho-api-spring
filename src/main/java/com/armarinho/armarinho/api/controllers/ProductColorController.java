package com.armarinho.armarinho.api.controllers;

import com.armarinho.armarinho.api.models.ProductColor;
import com.armarinho.armarinho.api.services.ProductColorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/product-colors")
@RestController
public class ProductColorController {
    private final ProductColorService service;
    public ProductColorController(ProductColorService service) {
        this.service = service;
    }

    @PostMapping
    public ProductColor create(@RequestBody ProductColor productColor) {
        productColor = service.create(productColor);
        return productColor;
    }

    @GetMapping
    public List<ProductColor> getAll() {
        List<ProductColor> allProductColors = service.getAll();
        return allProductColors;
    }

    @GetMapping("/{id}")
    public ProductColor getOne(@PathVariable("id") int id) {
        ProductColor productColor = service.getOne(id);
        return productColor;
    }

    @PutMapping("/{id}")
    public ProductColor update(@PathVariable("id") int id, @RequestBody ProductColor productColor) {
        productColor = service.update(id, productColor);
        return productColor;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
