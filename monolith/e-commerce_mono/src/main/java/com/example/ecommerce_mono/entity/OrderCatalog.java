package com.example.ecommerce_mono.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class OrderCatalog {
    @Id @GeneratedValue
    @Column(name = "order_catalog_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int totalPrice;
    private int qty;
    private Date createdAt;

    public static OrderCatalog createOrderCatalog(Catalog catalog, int qty) {
        OrderCatalog orderCatalog = new OrderCatalog();
        orderCatalog.setCatalog(catalog);
        orderCatalog.setQty(qty);
        orderCatalog.setTotalPrice(catalog.getUnitPrice()*qty);
        catalog.removeStock(qty);
        return orderCatalog;
    }
}
