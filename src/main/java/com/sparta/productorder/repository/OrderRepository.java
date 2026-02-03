package com.sparta.productorder.repository;

import com.sparta.productorder.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    Orders findByOrderId(long orderId);

    @EntityGraph(attributePaths = {"product"})
    Page<Orders> findAll(Pageable pageable);
}
