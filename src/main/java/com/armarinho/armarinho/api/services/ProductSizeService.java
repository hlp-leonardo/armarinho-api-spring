package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.dtos.ProductSizeDTO;
import com.armarinho.armarinho.api.models.ProductSize;
import com.armarinho.armarinho.api.repository.ProductSizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSizeService {
    private final ProductSizeRepository repository;
    public ProductSizeService(ProductSizeRepository repository) {
        this.repository = repository;
    }

    public ProductSizeDTO create(ProductSize productSize) throws Exception {
        return null;
    }

    public List<ProductSizeDTO> getAll() throws Exception {
        return null;
    }

    public ProductSizeDTO getOne(int id) throws Exception {
        return null;
    }

    public ProductSizeDTO update(int id, ProductSize productSize) throws Exception {
        return null;
    }

    public void delete(int id) throws Exception {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("");
        }
    }
}
