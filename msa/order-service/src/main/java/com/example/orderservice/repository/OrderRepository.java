package com.example.orderservice.repository;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Iterable<Order> findByUserId(String userId);

//    Iterable<OrderDto> findByProductId(String productId);
}
