package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.models.Product;
import com.armarinho.armarinho.api.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;
    public ProductService(ProductRepository repository) {
            this.repository = repository;
    }

    public Product create(@RequestBody Product product) {
        product = repository.save(product);
        return product;
    }

    public List<Product> getAll() {
        List<Product> allProducts = repository.findAll();
        return allProducts;
    }

    public Optional<Product> getOne(@PathVariable("id") int id) {
        Optional<Product> product = repository.findById(id);
        return product;
    }

    public Product update(@PathVariable("id") int id, @RequestBody Product product) {
        Product existingProduct = new Product();
        existingProduct.setId(id);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        product = repository.save(existingProduct);
        return product;
    }

    public void delete(@PathVariable("id") int id) {
        repository.deleteById(id);
    }
}
