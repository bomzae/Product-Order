package com.sparta.productorder.controller;

import com.sparta.productorder.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{productId}")
    public ResponseEntity postOrder(@PathVariable String productId) {
        return orderService.postOrder(productId);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity getOrder(@PathVariable long orderId) {
        return orderService.getOrder(orderId);
    }
}
