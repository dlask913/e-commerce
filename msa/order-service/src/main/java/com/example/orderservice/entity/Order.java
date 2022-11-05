package com.example.orderservice.entity;

import com.example.orderservice.dto.OrderDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String productId;

    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false)
    private Integer totalPrice;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false,unique = true)
    private String orderId;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createdAt;


    public static Order createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        order.setOrderId(UUID.randomUUID().toString());
        order.setQty(orderDto.getQty());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setUnitPrice(orderDto.getUnitPrice());
        order.setProductId(orderDto.getProductId());
        return order;
    }
}
