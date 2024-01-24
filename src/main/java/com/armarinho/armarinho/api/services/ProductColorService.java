package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.models.Product;
import com.armarinho.armarinho.api.models.ProductColor;
import com.armarinho.armarinho.api.repository.ProductColorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductColorService {
        private final ProductColorRepository repository;
    public ProductColorService(ProductColorRepository repository) {
        this.repository = repository;
    }

    public ProductColor create(ProductColor productColor) {
        productColor = repository.save(productColor);
        return productColor;
    }

    public List<ProductColor> getAll() {
        List<ProductColor> allProductColors = repository.findAll();
        return allProductColors;
    }

    public ProductColor getOne(int id) {
        Optional<ProductColor> productColor = repository.findById(id);
        if (productColor.isPresent()) {
            return productColor.get();
        } else {
            return null;
        }
    }

    public ProductColor update(int id, ProductColor productColor) {
        Optional<ProductColor> existingProductColor = repository.findById(id);
        if (existingProductColor.isPresent()) {
            productColor.setId(existingProductColor.get().getId());
            productColor.setName(productColor.getName());
            productColor = repository.save(productColor);
            return productColor;
        } else {
            return null;
        }
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
