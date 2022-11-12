package com.example.orderservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @Data
public class OrderDto {
    private String productId;
    private Integer qty;
    private Integer stock;
    private Integer unitPrice;
    private String userId;
    private Integer totalPrice;
}

