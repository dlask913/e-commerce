package com.example.ecommerce_mono.entity;

import com.example.ecommerce_mono.dto.OrderDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity @Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Date createdAt;

    private String productName; // 상품 이름
    private int qty; // 주문 수량
    private int stock;
    private int unitPrice;
    private int totalPrice;


    public static Order createOrder(Member member, OrderDto orderDto) {
        Order order = new Order();
        order.setMember(member);
        order.setQty(orderDto.getQty());
        order.setStock(orderDto.getStock());
        order.setProductName(orderDto.getProductName());
        order.setUnitPrice(orderDto.getUnitPrice());
        order.setTotalPrice(orderDto.getQty()* orderDto.getUnitPrice());
        order.setCreatedAt(new Date());
        return order;
    }
}
