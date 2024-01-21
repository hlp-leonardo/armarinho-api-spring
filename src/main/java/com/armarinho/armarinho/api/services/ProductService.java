package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.dtos.ProductDTO;
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

    public ProductDTO create(@RequestBody Product product) {
        product = repository.save(product);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productDTO.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    public List<Product> getAll() {
        List<Product> allProducts = repository.findAll();
        return allProducts;
    }
}
