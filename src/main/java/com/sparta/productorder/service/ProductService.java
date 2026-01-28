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

import java.util.List;

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

    // 상품 단건 조회
    public ResponseEntity<DefaultResponse> getProduct(String productId) {
        Product product = productRepository.findByProductId(productId);

        if (product == null) {
            return new ResponseEntity<>(DefaultResponse.from(StatusCode.NOT_FOUND, "존재하지 않는 상품입니다."), HttpStatus.OK);
        }

        String response = "상품 ID: " + product.getProductId() + "\n상품 이름: " + product.getName() + "\n상품 가격: " + product.getPrice();
        return new ResponseEntity<>(DefaultResponse.from(StatusCode.OK, "상품 조회 결과입니다.", response), HttpStatus.OK);

    }


    // 상품 목록 조회
    public ResponseEntity<DefaultResponse> getProducts() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            return new ResponseEntity<>(DefaultResponse.from(StatusCode.NOT_FOUND, "상품이 존재하지 않습니다."), HttpStatus.OK);
        }

        Product product; StringBuilder response = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            product = products.get(i);

            response.append("상품 ID: ").append(product.getProductId())
                    .append("\n상품 이름: ").append(product.getName())
                    .append("\n상품 가격: ").append(product.getPrice()).append("\n\n");
        }

        return new ResponseEntity<>(DefaultResponse.from(StatusCode.OK, "상품 목록 조회 결과입니다.", response.toString()), HttpStatus.OK);
    }
}
