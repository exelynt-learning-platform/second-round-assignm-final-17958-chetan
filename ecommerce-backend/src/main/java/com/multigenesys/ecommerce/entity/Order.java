package com.multigenesys.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private double totalPrice;
    private String shippingAddress;
    private String paymentStatus;
    private String orderStatus;
    private LocalDateTime timestamp;
}
