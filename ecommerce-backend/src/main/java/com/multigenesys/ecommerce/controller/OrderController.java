package com.multigenesys.ecommerce.controller;

import com.multigenesys.ecommerce.entity.Order;
import com.multigenesys.ecommerce.entity.User;
import com.multigenesys.ecommerce.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public Order createOrder(@RequestAttribute User user,
                             @RequestParam String shippingAddress){

        return orderService.createOrder(user, shippingAddress);
    }

    @GetMapping
    public List<Order> getOrders(@RequestAttribute User user){
        return orderService.getUserOrders(user);
    }
}