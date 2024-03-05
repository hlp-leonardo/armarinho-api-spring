package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.dtos.ProductColorDTO;
import com.armarinho.armarinho.api.models.ProductColor;
import com.armarinho.armarinho.api.repository.ProductColorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductColorService {

    private final ProductColorRepository repository;
    public ProductColorService(ProductColorRepository repository) {
        this.repository = repository;
    }

    private ProductColorDTO convertToProductColorDTO(ProductColor productColor) {
        ProductColorDTO productColorDTO = new ProductColorDTO();
        productColorDTO.setId(productColor.getId());
        productColorDTO.setName(productColor.getName());
        return productColorDTO;
    }

    private List<ProductColorDTO> convertListToProductColorDTO(List<ProductColor> productColorList) {
        List<ProductColorDTO> allProductColorsDTO = new ArrayList<>();
        for (ProductColor color : productColorList) {
            ProductColor existingProductColor = color;
            if (existingProductColor != null) {
                ProductColorDTO productColorDTO = new ProductColorDTO();
                productColorDTO.setId(existingProductColor.getId());
                productColorDTO.setName(existingProductColor.getName());
                allProductColorsDTO.add(productColorDTO);
            }
        }
        /*
        for (int i=0; i<productColorList.size(); i++) {
            ProductColor existingProductColor = productColorList.get(i);
            if (existingProductColor != null) {
                ProductColorDTO productColorDTO = new ProductColorDTO();
                productColorDTO.setId(existingProductColor.getId());
                productColorDTO.setName(existingProductColor.getName());
                allProductColorsDTO.add(productColorDTO);
            }
        }
        */
        return allProductColorsDTO;
    }

    private void checkIdNull(Integer id) throws Exception {
        if (id==null) {
            throw new Exception("ProductColor id null or invalid.");
        }
    }

    private void checkIfNameIsBlank(String name) throws Exception {
        if (name.isEmpty()) {
            throw new Exception("ProductColor name can not be blank.");
        }
    }

    private void checkIfNameExists(String name) throws Exception {
        List<ProductColor> allProductColors = repository.findAll();
        for (ProductColor color : allProductColors) {
            ProductColor existingProductColor = color;
            if (existingProductColor.getName().equals(name)) {
                throw new Exception("ProductColor name already exists.");
            }
        }
        /*
        for (int i=0; i< allProductColors.size(); i++) {
            ProductColor existingProductColor = allProductColors.get(i);
            if (existingProductColor.getName().equals(name)) {
                throw new Exception("ProductColor name already exists.");
            }
        }
        */
    }

    public ProductColorDTO create(ProductColor productColor) throws Exception {
        productColor.setName(productColor.getName().trim());
        String name = productColor.getName();
        checkIfNameIsBlank(name);
        checkIfNameExists(name);
        try {
            productColor = repository.save(productColor);
            ProductColorDTO productColorDTO = convertToProductColorDTO(productColor);
            return productColorDTO;
        } catch (Exception e) {
            throw new Exception("ProductColor could not be created.");
        }
    }

    public List<ProductColorDTO> getAll()throws Exception {
        try {
            List<ProductColor> allProductColors = repository.findAll();
            List<ProductColorDTO> allProductColorsDTO = convertListToProductColorDTO(allProductColors);
            return allProductColorsDTO;
        } catch (Exception e) {
            throw new Exception("ProductColor list could not be displayed.");
        }
    }

    public ProductColorDTO getOne(int id) throws Exception {
        checkIdNull(id);
        try {
            Optional<ProductColor> productColor = repository.findById(id);
            if (productColor.isPresent()) {
                ProductColorDTO productColorDTO = convertToProductColorDTO(productColor.get());
                return productColorDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("ProductColor could not be displayed.");
        }
    }

    public ProductColorDTO update(int id, ProductColor productColor) throws Exception {
        checkIdNull(id);
        productColor.setName(productColor.getName().trim());
        String name = productColor.getName();
        checkIfNameIsBlank(name);
        checkIfNameExists(name);
        try {
            Optional<ProductColor> existingProductColor = repository.findById(id);
            if (existingProductColor.isPresent()) {
                existingProductColor.get().setName(productColor.getName());
                repository.save(existingProductColor.get());
                ProductColorDTO productColorDTO = convertToProductColorDTO(existingProductColor.get());
                return productColorDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("ProductColor could not be updated.");
        }
    }

    public void delete(int id) throws Exception {
        checkIdNull(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("ProductColor could not be deleted.");
        }
    }
}
