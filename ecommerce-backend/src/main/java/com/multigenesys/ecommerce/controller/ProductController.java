package com.multigenesys.ecommerce.controller;

import com.multigenesys.ecommerce.entity.Product;
import com.multigenesys.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @GetMapping
    public List<Product> allProducts(){
        return productService.getAllProducts();
    }
}
