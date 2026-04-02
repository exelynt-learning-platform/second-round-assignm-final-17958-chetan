package com.multigenesys.ecommerce.repository;

import com.multigenesys.ecommerce.entity.Order;
import com.multigenesys.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
