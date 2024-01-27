package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.dtos.ProductTypeDTO;
import com.armarinho.armarinho.api.models.ProductType;
import com.armarinho.armarinho.api.repository.ProductTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {
    private final ProductTypeRepository repository;
    public ProductTypeService(ProductTypeRepository repository) {
        this.repository = repository;
    }

    private ProductTypeDTO convertToProductTypeDTO(ProductType productType) {
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        productTypeDTO.setId(productType.getId());
        productTypeDTO.setName(productType.getName());
        return productTypeDTO;
    }

    private List<ProductTypeDTO> convertListToProductTypeDTO(List<ProductType> productTypeList) {
        List<ProductTypeDTO> allProductTypesDTO = new ArrayList<>();
        for (int i=0; i<productTypeList.size(); i++) {
            ProductType existingProductType = productTypeList.get(i);
            if (existingProductType != null) {
                ProductTypeDTO productTypeDTO = new ProductTypeDTO();
                productTypeDTO.setId(existingProductType.getId());
                productTypeDTO.setName(existingProductType.getName());
                allProductTypesDTO.add(productTypeDTO);
            }
        }
        return allProductTypesDTO;
    }

    private void checkIdNull(Integer id) throws Exception {
        if (id==null) {
            throw new Exception("ProductType id null or invalid.");
        }
    }

    private void checkIfNameIsBlank(ProductType productType) throws Exception {
        productType.setName(productType.getName().trim());
        String checkName = productType.getName();
        if (checkName.isEmpty()) {
            throw new Exception("ProductType name can not be blank.");
        }
    }

    private void checkIfNameExists(ProductType productType) throws Exception {
        productType.setName(productType.getName());
        String newName = productType.getName();
        List<ProductType> allProductTypes = repository.findAll();
        for (int i=0; i< allProductTypes.size(); i++) {
            ProductType existingProductType = allProductTypes.get(i);
            if (existingProductType.getName().equals(newName)) {
                throw new Exception("ProductType name already exists.");
            }
        }
    }

    public ProductTypeDTO create(ProductType productType) throws Exception {
        checkIfNameIsBlank(productType);
        checkIfNameExists(productType);
        try {
            productType = repository.save(productType);
            ProductTypeDTO productTypeDTO = convertToProductTypeDTO(productType);
            return productTypeDTO;
        } catch (Exception e) {
            throw new Exception("ProductType name could not be created.");
        }
    }

    public List<ProductTypeDTO> getAll() throws Exception {
        try {
            List<ProductType> allProductTypes = repository.findAll();
            List<ProductTypeDTO> allProductTypesDTO = convertListToProductTypeDTO(allProductTypes);
            return allProductTypesDTO;
        } catch (Exception e) {
            throw new Exception("ProductType list could not be displayed.");
        }
    }

    public ProductTypeDTO getOne(int id) throws Exception {
        checkIdNull(id);
        try {
            Optional<ProductType> productType = repository.findById(id);
            if (productType.isPresent()) {
                ProductTypeDTO productTypeDTO = convertToProductTypeDTO(productType.get());
                return productTypeDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("ProductType could not be displayed.");
        }
    }

    public ProductTypeDTO update(int id, ProductType productType) throws Exception {
        checkIdNull(id);
        checkIfNameExists(productType);
        checkIfNameIsBlank(productType);
        try {
            Optional<ProductType> existingProductType = repository.findById(id);
            if (existingProductType.isPresent()) {
                existingProductType.get().setName(productType.getName());
                repository.save(existingProductType.get());
                ProductTypeDTO productTypeDTO = convertToProductTypeDTO(existingProductType.get());
                return productTypeDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("ProductType could not be updated.");
        }
    }

    public void delete(int id) throws Exception {
        checkIdNull(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("ProductType could not be deleted.");
        }
    }
}
