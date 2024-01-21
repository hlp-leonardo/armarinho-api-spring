package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.models.ProductType;
import com.armarinho.armarinho.api.repository.ProductTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {
    private final ProductTypeRepository repository;
    public ProductTypeService(ProductTypeRepository repository) {
        this.repository = repository;
    }

    public ProductType create(@RequestBody ProductType productType) {
        productType = repository.save(productType);
        return productType;
    }

    public List<ProductType> getAll() {
        List<ProductType> allProductTypes = repository.findAll();
        return allProductTypes;
    }

    public Optional<ProductType> getOne(@PathVariable("id") int id) {
        Optional<ProductType> productType = repository.findById(id);
        if (productType != null) {
            return productType;
        } else {
            return null;
        }
    }

    public ProductType update(int id, @RequestBody ProductType productType) {
        ProductType existingProductType = new ProductType();
        existingProductType.setId(id);
        existingProductType.setName(productType.getName());
        productType = repository.save(existingProductType);
        return productType;
    }

    public void delete(@PathVariable("id") int id) {
        repository.deleteById(id);
    }
}
