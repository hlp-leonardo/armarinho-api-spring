package com.armarinho.armarinho.api.services;

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

    public ProductColor create(@RequestBody ProductColor productColor) {
        productColor = repository.save(productColor);
        return productColor;
    }

    public List<ProductColor> getAll() {
        List<ProductColor> allProductColors = repository.findAll();
        return allProductColors;
    }

    public Optional<ProductColor> getOne(@PathVariable("id") int id) {
        Optional<ProductColor> productColor = repository.findById(id);
        if (productColor != null) {
            return productColor;
        } else {
            return null;
        }
    }

    public ProductColor update(int id, @RequestBody ProductColor productColor) {
        ProductColor existingProductColor = new ProductColor();
        existingProductColor.setId(id);
        existingProductColor.setName(productColor.getName());
        productColor = repository.save(existingProductColor);
        return productColor;
    }

    public void delete(@PathVariable("id") int id) {
        repository.deleteById(id);
    }
}
