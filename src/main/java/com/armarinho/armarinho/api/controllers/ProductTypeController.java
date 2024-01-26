package com.armarinho.armarinho.api.controllers;

import com.armarinho.armarinho.api.dtos.ProductTypeDTO;
import com.armarinho.armarinho.api.models.ProductType;
import com.armarinho.armarinho.api.services.ProductTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/product-types")
@RestController
public class ProductTypeController {
    private final ProductTypeService service;
    public ProductTypeController(ProductTypeService service) {
        this.service = service;
    }

    @PostMapping
    public ProductTypeDTO create(@RequestBody ProductType productType) {
        ProductTypeDTO createProductType = service.create(productType);
        return createProductType;
    }

    @GetMapping
    public List<ProductTypeDTO> getAll() {
        List<ProductTypeDTO> allProductTypes = service.getAll();
        return allProductTypes;
    }

    @GetMapping("/{id}")
    public ProductTypeDTO getOne(@PathVariable("id") int id) {
        ProductTypeDTO productType = service.getOne(id);
        return productType;
    }

    @PutMapping("/{id}")
    public ProductTypeDTO update(@PathVariable("id") int id, @RequestBody ProductType productType) {
        ProductTypeDTO productTypeDTO = service.update(id, productType);
        return productTypeDTO;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
