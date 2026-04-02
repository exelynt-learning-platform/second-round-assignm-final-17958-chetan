package com.multigenesys.ecommerce.repository;

import com.multigenesys.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //methods
    Optional<User> findByEmail(String email);
}
