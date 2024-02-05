package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.dtos.ProductDTO;
import com.armarinho.armarinho.api.dtos.SaleDTO;
import com.armarinho.armarinho.api.models.Product;
import com.armarinho.armarinho.api.models.Sale;
import com.armarinho.armarinho.api.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    private final SaleRepository repository;
    public SaleService(SaleRepository repository) {
        this.repository = repository;
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

    public SaleDTO create(Sale sale) throws Exception {
        return null;
    }

    public List<SaleDTO> getAll() throws Exception {
        try {
            List<Sale> allSales = repository.findAll();
            List<SaleDTO> allSalesDTO = convertListToSaleDTO(allSales);
            return allSalesDTO;
        } catch (Exception e) {
            throw new Exception("");
        }
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
