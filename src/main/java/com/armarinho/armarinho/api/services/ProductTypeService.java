package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.models.ProductType;
import com.armarinho.armarinho.api.repository.ProductTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ProductType getOne(int id) {
        Optional<ProductType> productType = repository.findById(id);
        if (productType.isPresent()) {
            return productType.get();
        } else {
            return null;
        }
    }

    public ProductType update(int id, ProductType productType) {
        Optional<ProductType> existingProductType = repository.findById(id);
        if (existingProductType.isPresent()) {
            productType.setId(existingProductType.get().getId());
            productType.setName(existingProductType.get().getName());
            productType = repository.save(productType);
            return productType;
        } else  {
            return null;
        }
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
