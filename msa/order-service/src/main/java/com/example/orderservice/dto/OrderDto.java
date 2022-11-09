package com.example.orderservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OrderDto {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private String orderId;
    private String userId;

}

