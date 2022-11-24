package com.example.ecommerce_mono.entity;

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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderCatalog> orderCatalogs = new ArrayList<>();

    public void addOrderCatalog(OrderCatalog orderCatalog) {
        orderCatalogs.add(orderCatalog);
        orderCatalog.setOrder(this);
    }

    public static Order createOrder(Member member, List<OrderCatalog> orderCatalogList) {
        Order order = new Order();
        order.setMember(member);
        for (OrderCatalog orderCatalog : orderCatalogList) {
            order.addOrderCatalog(orderCatalog);
        }
        order.setCreatedAt(new Date());
        return order;
    }
}
