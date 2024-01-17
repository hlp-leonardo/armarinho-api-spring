package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.models.ProductType;
import com.armarinho.armarinho.api.repository.ProductTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {
    private final ProductTypeRepository repository;
    public ProductTypeService(ProductTypeRepository repository) {
        this.repository = repository;
    }

    public ProductType create(ProductType productType) {
        productType = repository.save(productType);
        return productType;
    }

    public List<ProductType> getAll() {
        List<ProductType> allProductTypes = repository.findAll();
        return allProductTypes;
    }
}
