package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.dtos.*;
import com.armarinho.armarinho.api.models.Product;
import com.armarinho.armarinho.api.models.Sale;
import com.armarinho.armarinho.api.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    private final SaleRepository repository;
    public SaleService(SaleRepository repository) {
        this.repository = repository;
    }

    private SaleDTO convertToSaleDTO(Sale sale) {
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setId(sale.getId());
        saleDTO.setDate(sale.getDate().toInstant());
        return saleDTO;
    }

    private List<SaleDTO> convertListToSaleDTO(List<Sale> saleList) {
        List<SaleDTO> allSalesDTO = new ArrayList<>();
        for (int i=0; i<saleList.size(); i++) {
            Sale existingSale = saleList.get(i);
            if (existingSale != null) {
                SaleDTO saleDTO = new SaleDTO();
                saleDTO.setId(existingSale.getId());
                saleDTO.setDate(existingSale.getDate().toInstant());
                ProductDTO productDTO = convertToProductDTO(existingSale.getProducts().get(i));
                saleDTO.setProduct(productDTO);
                allSalesDTO.add(saleDTO);
            }
        }
        return allSalesDTO;
    }

    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    private void checkIdNull(Integer id) throws Exception {
        if (id==null) {
            throw new Exception("Sale id null or invalid.");
        }
    }

    public SaleDTO create(Sale sale) throws Exception {
        try {
            sale = repository.save(sale);
            SaleDTO saleDTO = convertToSaleDTO(sale);
            return saleDTO;
        } catch (Exception e) {
            throw new Exception("Sale could not be created.");
        }
    }

    public List<SaleDTO> getAll() throws Exception {
        try {
            List<Sale> allSales = repository.findAll();
            List<SaleDTO> allSalesDTO = convertListToSaleDTO(allSales);
            return allSalesDTO;
        } catch (Exception e) {
            throw new Exception("Sale list could not be displayed.");
        }
    }

    public SaleDTO getOne(int id) throws Exception {
        checkIdNull(id);
        try {
            Optional<Sale> sale = repository.findById(id);
            if (sale.isPresent()) {
                SaleDTO saleDTO = convertToProductDTO(sale.get());
                ProductDTO productDTO = convertToProductDTO(sale.get().getProducts());
                saleDTO.setProduct(productDTO);
                return saleDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Sale could not be displayed.");
        }
    }

    public SaleDTO update(int id, Sale sale) throws Exception {
        checkIdNull(id);
        try {
            Optional<Sale> existingSale = repository.findById(id);
            if (existingSale.isPresent()) {
                existingSale.get().setDate(sale.getDate());
                existingSale.get().setProducts(sale.getProducts());
                repository.save(existingSale.get());
                SaleDTO saleDTO = convertToSaleDTO(existingSale.get());
                ProductDTO productDTO = convertToProductDTO(sale.getProducts());
                saleDTO.setProduct(productDTO);
                return saleDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Sale could not be updated.");
        }
    }

    public void delete(int id) throws Exception {
        checkIdNull(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Sale could not be deleted.");
        }
    }
}
