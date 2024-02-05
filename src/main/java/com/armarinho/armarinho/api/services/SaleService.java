package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.dtos.SaleDTO;
import com.armarinho.armarinho.api.models.Sale;
import com.armarinho.armarinho.api.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    private final SaleRepository repository;
    public SaleService(SaleRepository repository) {
        this.repository = repository;
    }

    public SaleDTO create(Sale sale) throws Exception {
        return null;
    }

    public List<SaleDTO> getAll() throws Exception {
        return null;
    }

    public SaleDTO getOne(int id) throws Exception {
        return null;
    }

    public SaleDTO update(int id, Sale sale) throws Exception {
        return null;
    }

    public void delete(int id) throws Exception {

    }
}
