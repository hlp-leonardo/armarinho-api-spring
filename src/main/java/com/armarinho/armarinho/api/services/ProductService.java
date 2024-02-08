package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.dtos.ProductColorDTO;
import com.armarinho.armarinho.api.dtos.ProductDTO;
import com.armarinho.armarinho.api.dtos.ProductSizeDTO;
import com.armarinho.armarinho.api.dtos.ProductTypeDTO;
import com.armarinho.armarinho.api.models.Product;
import com.armarinho.armarinho.api.models.ProductColor;
import com.armarinho.armarinho.api.models.ProductSize;
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
        productDTO.setPrice(product.getPrice());
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
                ProductTypeDTO productTypeDTO = convertToProductTypeDTO(existingProduct.getProductType());
                productDTO.setProductTypeDTO(productTypeDTO);
                ProductColorDTO productColorDTO = convertToProductColorDTO(existingProduct.getProductColor());
                productDTO.setProductColorDTO(productColorDTO);
                ProductSizeDTO productSizeDTO = convertToProductZiseDTO(existingProduct.getProductSize());
                productDTO.setProductSizeDTO(productSizeDTO);
                allProductsDTO.add(productDTO);
            }
        }
        return allProductsDTO;
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
            throw new Exception("Product id null or invalid.");
        }
    }

    private void checkIfNameIsBlank(String name) throws Exception {
        if (name.isEmpty()) {
            throw new Exception("Product name can not be blank.");
        }
    }

    private void checkIfNameExists(String name) throws Exception {
        List<Product> allProducts = repository.findAll();
        for (int i=0; i< allProducts.size(); i++) {
            Product existingProduct = allProducts.get(i);
            if (existingProduct.getName().equals(name)) {
                throw new Exception("Product name already exists.");
            }
        }
    }

    public ProductDTO create(Product product) throws Exception {
        product.setName(product.getName().trim());
        String name = product.getName();
        checkIfNameIsBlank(name);
        checkIfNameExists(name);
        try {
            product = repository.save(product);
            ProductDTO productDTO = convertToProductDTO(product);
            ProductTypeDTO productTypeDTO = convertToProductTypeDTO(product.getProductType());
            productDTO.setProductTypeDTO(productTypeDTO);
            ProductColorDTO productColorDTO = convertToProductColorDTO(product.getProductColor());
            productDTO.setProductColorDTO(productColorDTO);
            ProductSizeDTO productSizeDTO = convertToProductZiseDTO(product.getProductSize());
            productDTO.setProductSizeDTO(productSizeDTO);
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
                ProductTypeDTO productTypeDTO = convertToProductTypeDTO(product.get().getProductType());
                productDTO.setProductTypeDTO(productTypeDTO);
                ProductColorDTO productColorDTO = convertToProductColorDTO(product.get().getProductColor());
                productDTO.setProductColorDTO(productColorDTO);
                ProductSizeDTO productSizeDTO = convertToProductZiseDTO(product.get().getProductSize());
                productDTO.setProductSizeDTO(productSizeDTO);
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
        product.setName(product.getName().trim());
        String name = product.getName();
        checkIfNameIsBlank(name);
        checkIfNameExists(name);
        try {
            Optional<Product> existingProduct = repository.findById(id);
            if (existingProduct.isPresent()) {
                existingProduct.get().setName(product.getName());
                existingProduct.get().setPrice(product.getPrice());
                existingProduct.get().setProductType(product.getProductType());
                existingProduct.get().setProductColor(product.getProductColor());
                existingProduct.get().setProductSize(product.getProductSize());
                repository.save(existingProduct.get());
                ProductDTO productDTO = convertToProductDTO(existingProduct.get());
                ProductTypeDTO productTypeDTO = convertToProductTypeDTO(product.getProductType());
                productDTO.setProductTypeDTO(productTypeDTO);
                ProductColorDTO productColorDTO = convertToProductColorDTO(product.getProductColor());
                productDTO.setProductColorDTO(productColorDTO);
                ProductSizeDTO productSizeDTO = convertToProductZiseDTO(product.getProductSize());
                productDTO.setProductSizeDTO(productSizeDTO);
                return productDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Product could not be updated.");
        }
    }

    public void delete(int id) throws Exception {
        checkIdNull(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Product could not be deleted.");
        }
    }
}
