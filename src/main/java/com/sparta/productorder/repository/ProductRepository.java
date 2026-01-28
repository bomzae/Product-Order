package com.sparta.productorder.repository;

import com.sparta.productorder.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByProductId(String productId);
}
