package com.armarinho.armarinho.api.controllers;

import com.armarinho.armarinho.api.dtos.ProductSizeDTO;
import com.armarinho.armarinho.api.models.ProductSize;
import com.armarinho.armarinho.api.services.ProductSizeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product-sizes")
@RestController
public class ProductSizeController {

    private final ProductSizeService service;
    public ProductSizeController(ProductSizeService service) {
        this.service = service;
    }

    @PostMapping
    public ProductSizeDTO create(@RequestBody  ProductSize productSize) throws Exception {
        ProductSizeDTO createProductSize = service.create(productSize);
        return createProductSize;
    }

    @GetMapping
    public List<ProductSizeDTO> getAll() throws Exception {
        List<ProductSizeDTO> allProductSizes = service.getAll();
        return allProductSizes;
    }

    @GetMapping("/{id}")
    public ProductSizeDTO getOne(@PathVariable("id") int id) throws Exception {
        ProductSizeDTO productSizeDTO = service.getOne(id);
        return productSizeDTO;
    }

    @PutMapping("/{id}")
    public ProductSizeDTO update(@PathVariable("id") int id, @RequestBody ProductSize productSize) throws Exception {
        ProductSizeDTO productSizeDTO = service.update(id, productSize);
        return productSizeDTO;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) throws Exception {
        service.delete(id);
    }
}
