package com.armarinho.armarinho.api.controllers;

import com.armarinho.armarinho.api.dtos.SaleDTO;
import com.armarinho.armarinho.api.models.Sale;
import com.armarinho.armarinho.api.services.SaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sales")
@RestController
public class SaleController {
    private final SaleService service;
    public SaleController(SaleService service) {
        this.service = service;
    }

    @PostMapping
    public SaleDTO create(@RequestBody Sale sale) throws Exception {
        SaleDTO createSale = service.create(sale);
        return createSale;
    }

    @GetMapping
    public List<SaleDTO> getAll() throws Exception {
        List<SaleDTO> allSales = service.getAll();
        return allSales;
    }

    @GetMapping("/{id}")
    public SaleDTO getOne(@PathVariable("id") int id) throws Exception {
        SaleDTO sale = service.getOne(id);
        return sale;
    }

    @PutMapping("/{id}")
    public SaleDTO update(@PathVariable("id") int id, @RequestBody Sale sale) throws Exception {
        SaleDTO updatetedSale = service.update(id, sale);
        return updatetedSale;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) throws Exception {
        service.delete(id);
    }
}
