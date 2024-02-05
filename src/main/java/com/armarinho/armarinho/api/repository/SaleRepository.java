package com.armarinho.armarinho.api.repository;

import com.armarinho.armarinho.api.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
