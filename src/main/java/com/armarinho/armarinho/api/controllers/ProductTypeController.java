package com.armarinho.armarinho.api.controllers;

import com.armarinho.armarinho.api.dtos.ProductTypeDTO;
import com.armarinho.armarinho.api.models.ProductType;
import com.armarinho.armarinho.api.services.ProductTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product-types")
@RestController
public class ProductTypeController {
    private final ProductTypeService service;
    public ProductTypeController(ProductTypeService service) {
        this.service = service;
    }

    @PostMapping
    public ProductTypeDTO create(@RequestBody ProductType productType) throws Exception {
        ProductTypeDTO createProductType = service.create(productType);
        return createProductType;
    }

    @GetMapping
    public List<ProductTypeDTO> getAll() throws Exception {
        List<ProductTypeDTO> allProductTypes = service.getAll();
        return allProductTypes;
    }

    @GetMapping("/{id}")
    public ProductTypeDTO getOne(@PathVariable("id") int id) throws Exception {
        ProductTypeDTO productType = service.getOne(id);
        return productType;
    }

    @PutMapping("/{id}")
    public ProductTypeDTO update(@PathVariable("id") int id, @RequestBody ProductType productType) throws Exception {
        ProductTypeDTO productTypeDTO = service.update(id, productType);
        return productTypeDTO;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) throws Exception {
        service.delete(id);
    }
}
