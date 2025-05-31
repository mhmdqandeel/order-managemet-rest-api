package com.qnadeel.springdemo.security.management.repositories;

import com.qnadeel.springdemo.security.management.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
