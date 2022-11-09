package com.example.orderservice.entity;

import com.example.orderservice.dto.OrderDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter @Setter @ToString
public class Order{

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String productId;
    @Column
    private Integer qty;
    @Column
    private Integer unitPrice;
    @Column
    private Integer totalPrice;
    @Column
    private String userId;

    @Column
    private Date createdAt;


    public static Order createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        order.setQty(orderDto.getQty());
        order.setTotalPrice(orderDto.getQty()*orderDto.getUnitPrice());
        order.setUnitPrice(orderDto.getUnitPrice());
        order.setProductId(orderDto.getProductId());
        order.setCreatedAt(new Date());
        return order;
    }
}
