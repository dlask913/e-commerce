package com.example.ecommerce_mono.service;

import com.example.ecommerce_mono.dto.CatalogDto;
import com.example.ecommerce_mono.dto.OrderDto;
import com.example.ecommerce_mono.entity.Catalog;
import com.example.ecommerce_mono.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service @Transactional
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogRepository catalogRepository;

    public Iterable<Catalog> getAllCatalogs(){
        return catalogRepository.findAll();
    }

    public void createCatalog(){
        for (int i = 1; i <= 10; i++) {
            Catalog catalog = new Catalog();
            catalog.setCreatedAt(new Date());
            catalog.setStock(i*10);
            catalog.setProductName("CATALOG_NAME_" + i);
            catalog.setUnitPrice(i*1000);
            catalogRepository.save(catalog);
        }
    }

    public Catalog findByProductName(String productName) {
        return catalogRepository.findByProductName(productName);
    }

    public Catalog findById(Long productId) {
       List<Catalog> catalogList = catalogRepository.findAll();
        for (Catalog catalog :
                catalogList) {
            if (catalog.getId().equals(productId)) {
                return catalog;
            }
        }
        return null;
    }
}
