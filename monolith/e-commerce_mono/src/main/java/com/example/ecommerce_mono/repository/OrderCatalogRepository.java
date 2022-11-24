package com.example.ecommerce_mono.repository;

import com.example.ecommerce_mono.entity.OrderCatalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCatalogRepository extends JpaRepository<OrderCatalog, Long> {
}
