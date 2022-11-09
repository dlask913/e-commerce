package com.example.orderservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class OrderDto {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private String userId;
}

