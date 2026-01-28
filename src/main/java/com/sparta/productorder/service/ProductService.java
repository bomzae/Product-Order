package com.sparta.productorder.service;

import com.sparta.productorder.context.StatusCode;
import com.sparta.productorder.dto.DefaultResponse;
import com.sparta.productorder.dto.ProductDto;
import com.sparta.productorder.entity.Product;
import com.sparta.productorder.repository.OrderRepository;
import com.sparta.productorder.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // 상품 등록
    public ResponseEntity<DefaultResponse> postProduct(ProductDto request) {
        Product product = productRepository.findByProductId(request.getProductId());
        if (product != null) {
            return new ResponseEntity<>(DefaultResponse.from(StatusCode.CONFLICT, "같은 아이디로 등록된 상품이 있습니다."), HttpStatus.OK);
        }

        product = Product.create(request.getProductId(), request.getName(), request.getPrice());
        productRepository.save(product);

        return new ResponseEntity<>(DefaultResponse.from(StatusCode.OK, "상품 등록에 성공하였습니다."), HttpStatus.OK);
    }
}
