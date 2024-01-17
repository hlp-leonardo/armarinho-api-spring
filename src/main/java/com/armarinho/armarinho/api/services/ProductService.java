package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.models.Product;
import com.armarinho.armarinho.api.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    public ProductService(ProductRepository repository) {
            this.repository = repository;
    }

    public Product create(@RequestBody Product product) {

        return product;
    }

    public List<Product> getAll() {

        List<Product> allProducts = repository.findAll();
        return allProducts;
    }
}
