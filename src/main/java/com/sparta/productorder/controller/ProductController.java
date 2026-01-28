package com.sparta.productorder.controller;

import com.sparta.productorder.dto.ProductDto;
import com.sparta.productorder.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity postProduct(@RequestBody ProductDto request) {
        return productService.postProduct(request);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity getProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/products")
    public ResponseEntity getProducts() {
        return productService.getProducts();
    }

    @PatchMapping("/product")
    public ResponseEntity modifyProduct(@RequestBody ProductDto request) {
        return productService.modifyProduct(request);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity deleteProduct(@PathVariable String productId) {
        return productService.deleteProduct(productId);
    }
}
