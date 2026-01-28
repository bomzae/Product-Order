package com.sparta.productorder.repository;

import com.sparta.productorder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Order, String> {
}
