package com.multigenesys.ecommerce.service;

import com.multigenesys.ecommerce.entity.Product;
import com.multigenesys.ecommerce.exception.ProductNotFoundException;
import com.multigenesys.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product saveProduct(Product product){
        return repository.save(product);
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product updateProduct(Long id,Product product){
        Product existing = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product with id " + id + " not found"));

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setQuantity(product.getQuantity());

        return repository.save(existing);
    }

    public void deleteProduct(Long id){
        Product product = repository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product with id " + id + " not found"));

        repository.delete(product);
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product with id " + id + " not found"));
    }
}
