package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.models.Product;
import com.armarinho.armarinho.api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;
    public ProductService(ProductRepository repository) {
            this.repository = repository;
    }

    public Product create(Product product) {
        product = repository.save(product);
        return product;
    }

    public List<Product> getAll() {
        List<Product> allProducts = repository.findAll();
        return allProducts;
    }

    public Product getOne(int id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        return null;
    }

    public Product update(int id, Product product) {
        Optional<Product> existingProduct = repository.findById(id);
        if (existingProduct.isPresent()) {
            product.setId(existingProduct.get().getId());
            product.setName(existingProduct.get().getName());
            product.setPrice(existingProduct.get().getPrice());
            product = repository.save(product);
            return product;
        } else {
            return null;
        }
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
