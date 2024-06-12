package com.example.be_api_web.service;

import com.example.be_api_web.dto.ProductDto;
import com.example.be_api_web.entity.product.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product>findAll();

    ResponseEntity<Product>saveEdit(ProductDto productDto);

    ResponseEntity<List<Product>>deleteProduct(Long id);

    ResponseEntity<?>searchAll(String input);

    ResponseEntity<?>saveProduct(ProductDto productDto);

    List<Object>ProductAll(Long id_product);

    ResponseEntity<?>getPrice(Float price1,Float price2);

    void deleteById(Long id);
}
