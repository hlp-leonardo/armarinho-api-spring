package com.armarinho.armarinho.api.services;

import com.armarinho.armarinho.api.dtos.ProductDTO;
import com.armarinho.armarinho.api.models.Product;
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

    private void checkIdNull(Integer id) throws Exception {
        if (id==null) {
            throw new Exception("Product id null or invalid.");
        }
    }

    private void checkObjectFields(Product product) throws Exception {
        product.setName(product.getName().trim());
        String checkName = product.getName();

        product.setPrice(product.getPrice());
        Double checkPrice = product.getPrice();

        if (checkName.isEmpty()){
            throw new Exception("Product name can not be blank.");
        } else if (checkPrice < 0  || checkPrice.toString().isEmpty()) {
            throw new Exception("Product price can not be blank or less than 0.");
        }
    }

    private void checkIfNameExists(Product product) throws Exception {
        product.setName(product.getName().trim());
        String newName = product.getName();

        List<Product> allProducts = new ArrayList<>();
        for (int i=0; i<allProducts.size(); i++) {
            Product existingProduct = allProducts.get(i);
            existingProduct.getName();
            if (existingProduct.getName().equals(newName)) {
                throw new Exception("Product name already exists.");
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////
    public ProductDTO create(Product product) throws Exception {
        checkObjectFields(product);
        checkIfNameExists(product);
        try {
            product = repository.save(product);

            ProductDTO createProductDTO = new ProductDTO();
            createProductDTO.setName(product.getName());
            createProductDTO.setPrice(product.getPrice());

            return createProductDTO;
        } catch (Exception e) {
            throw new Exception("Product could not be created.");
        }
    }
////////////////////////////////////////////////////////////////////////////////
        public List<ProductDTO> getAll() throws Exception {
            try {
                List<Product> allProducts = repository.findAll();
                List<ProductDTO> allProductsDTO = new ArrayList<>();

                for (int i=0; i< allProducts.size(); i++) {
                    Product existingProduct = allProducts.get(i);
                    if (existingProduct != null) {
                        ProductDTO existingProductDTO = new ProductDTO();
                        existingProductDTO.setId(existingProduct.getId());
                        existingProductDTO.setName(existingProduct.getName());
                        existingProductDTO.setPrice(existingProduct.getPrice());
                    }
                }
                return allProductsDTO;
            } catch (Exception e) {
                throw new Exception("Product could not be created.");
            }
        }
////////////////////////////////////////////////////////////////////////////////
    public Product getOne(int id) throws Exception {
        checkIdNull(id);
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        return null;
    }
////////////////////////////////////////////////////////////////////////////////
    public Product update(int id, Product product) throws Exception {
        checkIdNull(id);
        checkObjectFields(product);
        checkIfNameExists(product);
        Optional<Product> existingProduct = repository.findById(id);
        if (existingProduct.isPresent()) {
            product.setId(existingProduct.get().getId());
            product.setName(existingProduct.get().getName());
            product.setPrice(existingProduct.get().getPrice());
            product = repository.save(product);
            return product;
        } else {
            return null;
        }
    }
////////////////////////////////////////////////////////////////////////////////
    public void delete(int id) {
        repository.deleteById(id);
    }
}
