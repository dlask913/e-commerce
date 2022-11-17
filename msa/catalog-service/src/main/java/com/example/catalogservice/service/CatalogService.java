package com.example.catalogservice.service;

import com.example.catalogservice.entity.Catalog;
import com.example.catalogservice.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogRepository catalogRepository;

    public Iterable<Catalog> getAllCatalogs() {
        return catalogRepository.findAll();
    }

    public Catalog getCatalogByProductId(String productId) {
        return catalogRepository.findByProductId(productId);
    }

    public void createCatalog(){
        // product_id,product_name,stock,unit_price 'CATALOG-001','Berlin',100,1500);
        for (int i = 1; i <= 10; i++) {
            Catalog catalog = new Catalog();
            catalog.setProductId("CATALOG-00"+i);
            catalog.setStock(i*10);
            catalog.setProductName("CATALOG_NAME_" + i);
            catalog.setUnitPrice(i*1000);
            catalogRepository.save(catalog);
        }
    }
}
