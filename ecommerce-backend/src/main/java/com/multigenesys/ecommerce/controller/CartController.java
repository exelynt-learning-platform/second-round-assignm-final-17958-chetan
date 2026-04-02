package com.multigenesys.ecommerce.controller;

import com.multigenesys.ecommerce.entity.Cart;
import com.multigenesys.ecommerce.entity.User;
import com.multigenesys.ecommerce.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Cart getCart(@RequestAttribute User user){
        return cartService.getCart(user);
    }

    @PostMapping("/add")
    public Cart addToCart(@RequestAttribute User user,
                          @RequestParam Long productId,
                          @RequestParam Integer quantity){

        return cartService.addToCart(user, productId, quantity);
    }

    @DeleteMapping("/remove")
    public Cart removeFromCart(@RequestAttribute User user,
                               @RequestParam Long productId){

        return cartService.removeFromCart(user, productId);
    }
}