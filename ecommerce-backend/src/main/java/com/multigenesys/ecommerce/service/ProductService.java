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

    public Product updateProduct(Product product){
        return repository.findById(product.getId())
                .map(existing ->{
                    existing.setDescription(product.getDescription());
                    existing.setName(product.getName());
                    existing.setQuantity(product.getQuantity());
                    existing.setPrice(product.getPrice());

                    return repository.save(existing);
                })
                .orElse(saveProduct(product));
    }

    public void deleteProduct(Long id){
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()){
            repository.delete(product.get());
            return;
        }

        throw new ProductNotFoundException("Product with id : "+id+" not found");
    }
}
