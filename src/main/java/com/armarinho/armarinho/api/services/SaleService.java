package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.dtos.*;
import com.armarinho.armarinho.api.models.*;
import com.armarinho.armarinho.api.repository.ProductRepository;
import com.armarinho.armarinho.api.repository.SaleRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.collection.spi.PersistentList;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository repository;
    private final ProductRepository productRepository;

    public SaleService(SaleRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
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
                List <ProductDTO> productDTO = convertListToProductDTO(existingSale.getProducts());
                saleDTO.setProducts(productDTO);
                allSalesDTO.add(saleDTO);
            }
        }
        return allSalesDTO;
    }

    private List<ProductDTO> convertListToProductDTO(List<Product> products) {
        List<ProductDTO> productsDTO = new ArrayList<>();
        for (int i=0; i<products.size(); i++) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(products.get(i).getId());
            productDTO.setName(products.get(i).getName());
            productDTO.setPrice(products.get(i).getPrice());
            productDTO.setProductTypeDTO(convertToProductTypeDTO(products.get(i).getProductType()));
            productDTO.setProductColorDTO(convertToProductColorDTO(products.get(i).getProductColor()));
            productDTO.setProductSizeDTO(convertToProductZiseDTO(products.get(i).getProductSize()));
            productsDTO.add(productDTO);
        }
        return productsDTO;
    }

    private ProductTypeDTO convertToProductTypeDTO(ProductType productType) {
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        productTypeDTO.setId(productType.getId());
        productTypeDTO.setName(productType.getName());
        return productTypeDTO;
    }

    private ProductColorDTO convertToProductColorDTO(ProductColor productColor) {
        ProductColorDTO productColorDTO = new ProductColorDTO();
        productColorDTO.setId(productColor.getId());
        productColorDTO.setName(productColor.getName());
        return productColorDTO;
    }

    private ProductSizeDTO convertToProductZiseDTO(ProductSize productSize) {
        ProductSizeDTO productSizeDTO = new ProductSizeDTO();
        productSizeDTO.setId(productSize.getId());
        productSizeDTO.setName(productSize.getName());
        return productSizeDTO;
    }

    private void checkIdNull(Integer id) throws Exception {
        if (id==null) {
            throw new Exception("Sale id null or invalid.");
        }
    }

    public SaleDTO create(List<Integer> ids) throws Exception {
        try {
            List<Product> products = productRepository.findAllById(ids);
            Sale sale = new Sale();
            sale.setDate(Date.from(Instant.now()));
            sale = repository.save(sale);
            //sale.setProducts(products);
            //repository.save(sale);
            for (int i=0; i< products.size(); i++) {
                products.get(i).getSales().add(sale);
            }
            sale.setProducts(products);
            repository.save(sale);
            SaleDTO saleDTO = convertToSaleDTO(sale);
            List<ProductDTO> productsDTO = convertListToProductDTO(products);
            saleDTO.setProducts(productsDTO);
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
                SaleDTO saleDTO = convertToSaleDTO(sale.get());
                List<ProductDTO> productDTO = convertListToProductDTO(sale.get().getProducts());
                saleDTO.setProducts(productDTO);
                return saleDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Sale could not be displayed.");
        }
    }

    public SaleDTO update(int id, List<Integer> ids) throws Exception {
        checkIdNull(id);
        try {
            Optional<Sale> sale = repository.findById(id);
            if (sale.isPresent()) {
                sale.get().setDate(Date.from(Instant.now()));
                List<Product> products = productRepository.findAllById(ids);
                for (int i=0; i<products.size(); i++) {
                    products.get(i).getSales().add(sale.get());
                }
                sale.get().setProducts(products);
                repository.save(sale.get());
                SaleDTO saleDTO = convertToSaleDTO(sale.get());
                List<ProductDTO> productDTO = convertListToProductDTO(products);
                saleDTO.setProducts(productDTO);
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
