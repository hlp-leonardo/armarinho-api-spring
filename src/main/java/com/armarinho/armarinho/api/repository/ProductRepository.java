package com.armarinho.armarinho.api.repository;

import com.armarinho.armarinho.api.controllers.ProductController;
import com.armarinho.armarinho.api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
