package com.sparta.productorder.controller;

import com.sparta.productorder.dto.ProductDto;
import com.sparta.productorder.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity postProduct(@RequestBody ProductDto request) {
        return productService.postProduct(request);
    }
}
