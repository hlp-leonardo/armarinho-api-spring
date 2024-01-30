package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.dtos.ProductSizeDTO;
import com.armarinho.armarinho.api.models.ProductSize;
import com.armarinho.armarinho.api.repository.ProductSizeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSizeService {
    private final ProductSizeRepository repository;
    public ProductSizeService(ProductSizeRepository repository) {
        this.repository = repository;
    }

    private ProductSizeDTO convertToProductSizeDTO(ProductSize productSize) {
        ProductSizeDTO productSizeDTO = new ProductSizeDTO();
        productSizeDTO.setId(productSize.getId());
        productSizeDTO.setName(productSize.getName());
        return productSizeDTO;
    }

    private List<ProductSizeDTO> convertListToProductSizeDTO(List<ProductSize> productSizeList) {
        List<ProductSizeDTO> allProductSizesDTO = new ArrayList<>();
        for (int i=0; i<productSizeList.size(); i++) {
            ProductSize existingProductSize = productSizeList.get(i);
            if (existingProductSize != null) {
                ProductSizeDTO productSizeDTO = new ProductSizeDTO();
                productSizeDTO.setId(existingProductSize.getId());
                productSizeDTO.setName(existingProductSize.getName());
                allProductSizesDTO.add(productSizeDTO);
            }
        }
        return allProductSizesDTO;
    }

    private void checkIdNull(Integer id) throws Exception {
        if (id==null) {
            throw new Exception("ProductSize id null or invalid.");
        }
    }

    private void checkIfNameIsBlank(String name) throws Exception {
        if (name.isEmpty()) {
            throw new Exception("ProductSize name can not be blank.");
        }
    }

    private void checkIfNameExists(String name) throws Exception {
        List<ProductSize> allProductSizes = repository.findAll();
        for (int i=0; i< allProductSizes.size(); i++) {
            ProductSize existingProductSize = allProductSizes.get(i);
            if (existingProductSize.getName().equals(name)) {
                throw new Exception("ProductSize name already exists.");
            }
        }
    }

    public ProductSizeDTO create(ProductSize productSize) throws Exception {
        productSize.setName(productSize.getName());
        String name = productSize.getName();
        checkIfNameIsBlank(name);
        checkIfNameExists(name);
        try {
            productSize = repository.save(productSize);
            ProductSizeDTO productSizeDTO = convertToProductSizeDTO(productSize);
            return productSizeDTO;
        } catch (Exception e) {
            throw new Exception("ProductSize could not be created.");
        }
    }

    public List<ProductSizeDTO> getAll() throws Exception {
        try {
            List<ProductSize> allProductSizes = repository.findAll();
            List<ProductSizeDTO> allProductSizesDTO = convertListToProductSizeDTO(allProductSizes);
            return allProductSizesDTO;
        } catch (Exception e) {
            throw new Exception("ProductSize list could not be displayed.");
        }
    }

    public ProductSizeDTO getOne(int id) throws Exception {
        checkIdNull(id);
        try {
            Optional<ProductSize> productSize = repository.findById(id);
            if (productSize.isPresent()) {
                ProductSizeDTO productSizeDTO = convertToProductSizeDTO(productSize.get());
                return productSizeDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("ProductSize could not be displayed.");
        }
    }

    public ProductSizeDTO update(int id, ProductSize productSize) throws Exception {
        checkIdNull(id);
        productSize.setName(productSize.getName());
        String name = productSize.getName();
        checkIfNameIsBlank(name);
        checkIfNameExists(name);
        try {
            Optional<ProductSize> existingProductSize = repository.findById(id);
            if (existingProductSize.isPresent()) {
                existingProductSize.get().setName(productSize.getName());
                repository.save(existingProductSize.get());
                ProductSizeDTO productSizeDTO = convertToProductSizeDTO(existingProductSize.get());
                return productSizeDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("ProductSize could not be updated.");
        }
    }

    public void delete(int id) throws Exception {
        checkIdNull(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("ProductSize could not be deleted.");
        }
    }
}
