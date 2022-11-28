package com.example.ecommerce_mono.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDto {
    private String userId;
    private String productName; // 상품 이름
    private int qty; // 주문 수량
    private int stock;
    private int unitPrice;
}
