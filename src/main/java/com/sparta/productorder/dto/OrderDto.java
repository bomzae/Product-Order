package com.sparta.productorder.dto;

import com.sparta.productorder.entity.Orders;
import lombok.Getter;

@Getter
public class OrderDto {
    private long orderId;
    private String name;
    private int price;

    public OrderDto(Orders orders) {
        this.orderId = orders.getOrderId();
        this.name = orders.getProduct().getName();
        this.price = orders.getProduct().getPrice();
    }
}
