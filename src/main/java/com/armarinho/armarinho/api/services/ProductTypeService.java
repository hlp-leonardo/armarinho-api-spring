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

    public ProductTypeDTO create(ProductType productType) {
        productType = repository.save(productType);
        ProductTypeDTO productTypeDTO = convertToProductTypeDTO(productType);
        return productTypeDTO;
    }

    public List<ProductTypeDTO> getAll() {
        List<ProductType> allProductTypes = repository.findAll();
        List<ProductTypeDTO> allProductTypesDTO = convertListToProductTypeDTO(allProductTypes);
        return allProductTypesDTO;
    }

    public ProductTypeDTO getOne(int id) {
        Optional<ProductType> productType = repository.findById(id);
        if (productType.isPresent()) {
            ProductTypeDTO productTypeDTO = convertToProductTypeDTO(productType.get());
            return productTypeDTO;
        } else {
            return null;
        }
    }

    public ProductTypeDTO update(int id, ProductType productType) {
        Optional<ProductType> existingProductType = repository.findById(id);
        if (existingProductType.isPresent()) {
            existingProductType.get().setName(productType.getName());
            repository.save(existingProductType.get());
            ProductTypeDTO productTypeDTO = convertToProductTypeDTO(existingProductType.get());
            return productTypeDTO;
        } else  {
            return null;
        }
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
