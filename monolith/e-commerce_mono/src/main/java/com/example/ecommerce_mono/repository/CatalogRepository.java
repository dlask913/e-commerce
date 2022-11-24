package com.example.ecommerce_mono.repository;

import com.example.ecommerce_mono.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Long> {
    Catalog findByProductName(String productName);

}
