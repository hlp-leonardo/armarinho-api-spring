package com.armarinho.armarinho.api.repository;

import com.armarinho.armarinho.api.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
}
