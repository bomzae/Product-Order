package com.sparta.productorder.service;

import com.sparta.productorder.context.StatusCode;
import com.sparta.productorder.dto.DefaultResponse;
import com.sparta.productorder.entity.Orders;
import com.sparta.productorder.entity.Product;
import com.sparta.productorder.repository.OrderRepository;
import com.sparta.productorder.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // 주문 생성
    @Transactional
    public ResponseEntity<DefaultResponse> postOrder(String productId) {
        Product product = productRepository.findByProductId(productId);

        if (product == null) {
            return new ResponseEntity<>(DefaultResponse.from(StatusCode.NOT_FOUND, "존재하지 않는 상품입니다."), HttpStatus.OK);
        }

        // 재고 차감
        int updateCount = productRepository.subStock(productId);
        // 재고가 없다면 주문 불가
        if (updateCount <= 0) {
            return new ResponseEntity<>(DefaultResponse.from(StatusCode.BAD_REQUEST, "상품 재고가 없습니다."), HttpStatus.OK);
        }

        Orders orders = Orders.create(product);
        orderRepository.save(orders);

        return new ResponseEntity<>(DefaultResponse.from(StatusCode.OK, "주문 등록에 성공하였습니다."), HttpStatus.OK);
    }

    // 주문 단건 조회
    public ResponseEntity<DefaultResponse> getOrder(long orderId) {
        Orders orders = orderRepository.findByOrderId(orderId);

        if (orders == null) {
            return new ResponseEntity<>(DefaultResponse.from(StatusCode.NOT_FOUND, "존재하지 않는 주문입니다."), HttpStatus.OK);
        }

        String response = String.format("주문 ID: %s, 상품 이름: %s, 상품 가격: %d원",
                orders.getOrderId(), orders.getProduct().getName(), orders.getProduct().getPrice());
        return new ResponseEntity<>(DefaultResponse.from(StatusCode.OK, "주문 조회 결과입니다.", response), HttpStatus.OK);
    }

    // 주문 목록 조회
    public ResponseEntity<DefaultResponse> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("orderId").ascending());
        Page<Orders> orders = orderRepository.findAll(pageable);

        List<String> response = new ArrayList<>();
        response.add(String.format("페이지: %d / %d", orders.getNumber() + 1, orders.getTotalPages()));

        for (Orders order: orders) {
            response.add(String.format("주문 ID: %s, 상품 이름: %s, 상품 가격: %d원",
                    order.getOrderId(), order.getProduct().getName(), order.getProduct().getPrice()));
        }

        return new ResponseEntity<>(DefaultResponse.from(StatusCode.OK, "주문 목록 조회 결과입니다.", response), HttpStatus.OK);
    }
}
