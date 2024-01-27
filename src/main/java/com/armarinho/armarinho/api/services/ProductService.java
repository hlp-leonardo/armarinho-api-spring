package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.dtos.ProductDTO;
import com.armarinho.armarinho.api.dtos.ProductTypeDTO;
import com.armarinho.armarinho.api.models.Product;
import com.armarinho.armarinho.api.models.ProductType;
import com.armarinho.armarinho.api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;
    public ProductService(ProductRepository repository) {
            this.repository = repository;
    }

    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        return productDTO;
    }

    private List<ProductDTO> convertListToProductDTO(List<Product> productList) {
        List<ProductDTO> allProductsDTO = new ArrayList<>();
        for (int i=0; i<productList.size(); i++) {
            Product existingProduct = productList.get(i);
            if (existingProduct != null) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(existingProduct.getId());
                productDTO.setName(existingProduct.getName());
                productDTO.setPrice(existingProduct.getPrice());
                allProductsDTO.add(productDTO);
            }
        }
        return allProductsDTO;
    }

    private void checkIdNull(Integer id) throws Exception {
        if (id==null) {
            throw new Exception("Product id null or invalid.");
        }
    }

    private void checkIfNameIsBlank(Product product) throws Exception {
        product.setName(product.getName().trim());
        String checkName = product.getName();
        if (checkName.isEmpty()) {
            throw new Exception("Product name can not be blank.");
        }
    }

    private void checkIfNameExists(Product product) throws Exception {
        product.setName(product.getName());
        String newName = product.getName();
        List<Product> allProducts = repository.findAll();
        for (int i=0; i< allProducts.size(); i++) {
            Product existingProduct = allProducts.get(i);
            if (existingProduct.getName().equals(newName)) {
                throw new Exception("Product name already exists.");
            }
        }
    }

    public ProductDTO create(Product product) throws Exception {
        checkIfNameIsBlank(product);
        checkIfNameExists(product);
        try {
            product = repository.save(product);
            ProductDTO productDTO = convertToProductDTO(product);
            return productDTO;
        } catch (Exception e) {
            throw new Exception("Product could not be created.");
        }
    }

    public List<ProductDTO> getAll() throws Exception {
        try {
            List<Product> allProducts = repository.findAll();
            List<ProductDTO> allProductsDTO = convertListToProductDTO(allProducts);
            return allProductsDTO;
        } catch (Exception e) {
            throw new Exception("Product list could not be displayed.");
        }
    }

    public ProductDTO getOne(int id) throws Exception {
        checkIdNull(id);
        try {
            Optional<Product> product = repository.findById(id);
            if (product.isPresent()) {
                ProductDTO productDTO = convertToProductDTO(product.get());
                return productDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Product could not be displayed.");
        }
    }

    public ProductDTO update(int id, Product product) throws Exception {
        checkIdNull(id);
        checkIfNameIsBlank(product);
        checkIfNameExists(product);
        try {
            Optional<Product> existingProduct = repository.findById(id);
            if (existingProduct.isPresent()) {
                existingProduct.get().setName(product.getName());
                ProductDTO productDTO = convertToProductDTO(existingProduct.get());
                return productDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Product could not be updated.");
        }
    }

    public void delete(int id) throws Exception {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Product could not be deleted.");
        }
    }
}
