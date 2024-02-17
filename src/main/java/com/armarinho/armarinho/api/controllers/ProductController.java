package com.armarinho.armarinho.api.controllers;

import com.armarinho.armarinho.api.dtos.ProductDTO;
import com.armarinho.armarinho.api.dtos.ProductTypeDTO;
import com.armarinho.armarinho.api.models.Product;
import com.armarinho.armarinho.api.models.ProductType;
import com.armarinho.armarinho.api.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ProductDTO create(@RequestBody Product product) throws Exception {
        ProductDTO createProduct = service.create(product);
        return createProduct;
    }

    @GetMapping
    public List<ProductDTO> getAll() throws Exception {
        List<ProductDTO> allProducts = service.getAll();
        return allProducts;
    }

    @GetMapping("/{id}")
    public ProductDTO getOne(@PathVariable("id") int id) throws Exception {
        ProductDTO product = service.getOne(id);
        return product;
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable("id") int id, @RequestBody Product product) throws Exception {
        ProductDTO updateProduct = service.update(id, product);
        return updateProduct;
    }

    @PutMapping("/{id}/productType/{typeId}")
    public ProductDTO update(@PathVariable("id") Product product, @PathVariable("typeId") int typeId) throws Exception {
        ProductDTO productTypeInProduct = service.update(typeId, product);
        return productTypeInProduct;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) throws Exception {
        service.delete(id);
    }
}
