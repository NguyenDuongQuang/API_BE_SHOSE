package com.example.be_api_web.service;

import com.example.be_api_web.entity.product.Product_Type;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Product_TypeService {
    List<Product_Type> findAll();

    ResponseEntity<Product_Type> editPro_Type(Product_Type updatePro_Type);

    ResponseEntity<?>savePro_Type(Product_Type savePro_Type);

    ResponseEntity<List<Product_Type>>deletePro_Type(Long id);

    List<Product_Type>searchPro_Type(Long id);
}
