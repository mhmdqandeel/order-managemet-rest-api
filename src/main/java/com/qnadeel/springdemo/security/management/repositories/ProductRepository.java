package com.qnadeel.springdemo.security.management.repositories;

import com.qnadeel.springdemo.security.management.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductName(String productName);

    Optional<Product> findByProductName(String productName);

    Optional<Product> findByProductId(Long productId);
}