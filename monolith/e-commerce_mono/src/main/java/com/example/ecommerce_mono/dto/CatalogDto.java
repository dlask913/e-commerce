package com.example.ecommerce_mono.dto;

import lombok.Data;

@Data
public class CatalogDto {

    private String productName;
    private int stock;
    private int unitPrice;
}
