package com.armarinho.armarinho.api.controllers;

import com.armarinho.armarinho.api.models.Product;
import com.armarinho.armarinho.api.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService service;
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        product = service.create(product);
        return product;
    }

    @GetMapping
    public List<Product> getAll() {
       List<Product> allProducts = service.getAll();
       return allProducts;
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable("id") int id) {
        Product product = service.getOne(id);
        return product;
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable("id") int id, @RequestBody Product product) {
        product = service.update(id, product);
        return product;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
