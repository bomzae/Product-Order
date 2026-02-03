package com.sparta.productorder.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(nullable = false)
    private String name;

    @Column
    private int price;

    @Column
    private int stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Orders> orders;

    public static Product create(String productId, String name, int price, int stock) {
        return Product.builder()
                .productId(productId)
                .name(name)
                .price(price)
                .stock(stock)
                .build();
    }
}
