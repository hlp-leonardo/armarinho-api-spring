package com.armarinho.armarinho.api.controllers;

import com.armarinho.armarinho.api.dtos.ProductColorDTO;
import com.armarinho.armarinho.api.models.ProductColor;
import com.armarinho.armarinho.api.services.ProductColorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/product-colors")
@RestController
public class ProductColorController {
    private final ProductColorService service;
    public ProductColorController(ProductColorService service) {
        this.service = service;
    }

    @PostMapping
    public ProductColorDTO create(@RequestBody ProductColor productColor) throws Exception {
        ProductColorDTO productColorDTO = service.create(productColor);
        return productColorDTO;
    }

    @GetMapping
    public List<ProductColorDTO> getAll() throws Exception {
        List<ProductColorDTO> allProductColors = service.getAll();
        return allProductColors;
    }

    @GetMapping("/{id}")
    public ProductColorDTO getOne(@PathVariable("id") int id) throws Exception {
        ProductColorDTO productColor = service.getOne(id);
        return productColor;
    }

    @PutMapping("/{id}")
    public ProductColorDTO update(@PathVariable("id") int id, @RequestBody ProductColor productColor) throws Exception {
        ProductColorDTO productColorDTO = service.update(id, productColor);
        return productColorDTO;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) throws Exception {
        service.delete(id);
    }
}
