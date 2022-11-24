package com.example.ecommerce_mono.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name = "catalog")
@Getter @Setter @ToString
public class Catalog {
    @Id @Column(name = "catalog_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private int stock;
    @Column(nullable = false)
    private int unitPrice;
    @Column(nullable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createdAt;

    public void removeStock(int qty){
        this.stock = this.stock-qty;
    }
}
