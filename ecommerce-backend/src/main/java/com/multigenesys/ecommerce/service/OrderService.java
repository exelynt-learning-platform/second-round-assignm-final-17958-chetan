package com.multigenesys.ecommerce.service;

import com.multigenesys.ecommerce.entity.*;
import com.multigenesys.ecommerce.exception.CartEmptyException;
import com.multigenesys.ecommerce.repository.CartRepository;
import com.multigenesys.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public OrderService(CartRepository cartRepository,
                        OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(User user, String shippingAddress){

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new CartEmptyException("Cart not found"));

        if(cart.getItems().isEmpty()){
            throw new CartEmptyException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(shippingAddress);
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderStatus("CREATED");
        order.setPaymentStatus("PENDING");

        List<OrderItem> orderItems = new ArrayList<>();

        double totalPrice = 0;

        for(CartItem cartItem : cart.getItems()){

            OrderItem item = new OrderItem();

            item.setOrder(order);
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(cartItem.getProduct().getPrice());

            totalPrice += cartItem.getQuantity() * cartItem.getProduct().getPrice();

            orderItems.add(item);
        }

        order.setItems(orderItems);
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(User user){
        return orderRepository.findByUser(user);
    }
}