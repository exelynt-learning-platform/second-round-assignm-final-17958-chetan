package com.multigenesys.ecommerce.service;

import com.multigenesys.ecommerce.entity.Cart;
import com.multigenesys.ecommerce.entity.CartItem;
import com.multigenesys.ecommerce.entity.Product;
import com.multigenesys.ecommerce.entity.User;
import com.multigenesys.ecommerce.exception.ProductNotFoundException;
import com.multigenesys.ecommerce.repository.CartRepository;
import com.multigenesys.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart getCart(User user){

        Optional<Cart> cart = cartRepository.findByUser(user);

        if(cart.isPresent()){
            return cart.get();
        }

        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setItems(new ArrayList<>());

        return cartRepository.save(newCart);
    }

    public Cart addToCart(User user, Long productId, Integer quantity){

        Cart cart = getCart(user);

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setCart(cart);

        cart.getItems().add(item);

        return cartRepository.save(cart);
    }

    public Cart removeFromCart(User user, Long productId){

        Cart cart = getCart(user);

        cart.getItems().removeIf(item ->
                item.getProduct().getId().equals(productId));

        return cartRepository.save(cart);
    }
}