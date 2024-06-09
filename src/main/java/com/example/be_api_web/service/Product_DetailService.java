package com.example.be_api_web.service;

import com.example.be_api_web.entity.product.Product_Detail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Product_DetailService {
    List<Product_Detail>findAllProductDetail();

    List<Product_Detail>findProductDetail();

    ResponseEntity<?>saveProductDetail(Product_Detail saveProDetail);

    ResponseEntity<Product_Detail>deleteProDetail(Long id);

    ResponseEntity<Product_Detail>editProductDetail(Product_Detail editProDetail);

    List<Product_Detail>searchAll(String keyword);

    ResponseEntity editQuantityProDetail(Product_Detail product_detail);
}
