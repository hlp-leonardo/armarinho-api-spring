package com.armarinho.armarinho.api.controllers;

import com.armarinho.armarinho.api.dtos.ProductDTO;
import com.armarinho.armarinho.api.models.Product;
import com.armarinho.armarinho.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
