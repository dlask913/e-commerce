package com.example.ecommerce_mono.repository;

import com.example.ecommerce_mono.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
