package com.sparta.productorder.repository;

import com.sparta.productorder.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByProductId(String productId);

    @Modifying(clearAutomatically = true) // 영속성 컨텍스트 1차 캐시 삭제
    @Query("UPDATE Product SET stock = stock - 1 WHERE productId = :productId AND stock > 0")
    int subStock(String productId); // 영향을 받은 행 수만큼 반환
}