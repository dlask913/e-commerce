package com.example.orderservice.vo;

import lombok.Data;

@Data
public class ResponseCatalog {
    private String productId;
    private Integer qty;
    private Integer unitPrice;

//    private String orderId;
}
