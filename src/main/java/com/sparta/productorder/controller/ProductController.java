package com.sparta.productorder.controller;

import com.sparta.productorder.dto.ProductDto;
import com.sparta.productorder.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity postProduct(@RequestBody ProductDto request) {
        return productService.postProduct(request);
    }

    @GetMapping("/{productId}")
    public ResponseEntity getProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/list")
    public ResponseEntity getProducts() {
        return productService.getProducts();
    }

    @PatchMapping("")
    public ResponseEntity modifyProduct(@RequestBody ProductDto request) {
        return productService.modifyProduct(request);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity deleteProduct(@PathVariable String productId) {
        return productService.deleteProduct(productId);
    }
}
