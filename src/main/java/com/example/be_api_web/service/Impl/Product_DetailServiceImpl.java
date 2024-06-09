package com.example.be_api_web.service.Impl;

import com.example.be_api_web.controller.message.Message;
import com.example.be_api_web.entity.product.Product_Detail;
import com.example.be_api_web.repository.product.ProductDetailRepository;
import com.example.be_api_web.service.Product_DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class Product_DetailServiceImpl implements Product_DetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public List<Product_Detail> findAllProductDetail() {
        return (List<Product_Detail>) ResponseEntity.ok().body(productDetailRepository.findAll());
    }

    @Override
    public List<Product_Detail> findProductDetail() {
        return (List<Product_Detail>) ResponseEntity.ok().body(productDetailRepository.findProductDetails());
    }

    @Override
    public ResponseEntity<?> saveProductDetail(Product_Detail saveProDetail) {
        try {
            Product_Detail product_detail=new Product_Detail();
            return ResponseEntity.ok(product_detail);
        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Product_Detail> deleteProDetail(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Product_Detail> editProductDetail(Product_Detail editProDetail) {
        return null;
    }

    @Override
    public List<Product_Detail> searchAll(String keyword) {
        return (List<Product_Detail>) ResponseEntity.ok().body(productDetailRepository.searchAll(keyword));
    }

    @Override
    public ResponseEntity editQuantityProDetail(Product_Detail product_detail) {
        Optional<Product_Detail> pro_detail = productDetailRepository.findById(product_detail.getId());
        if(pro_detail.isPresent()){
            Product_Detail productDetail=pro_detail.get();
            productDetail.setQuantity(product_detail.getQuantity());
            productDetailRepository.save(productDetail);
            return ResponseEntity.ok().body(productDetailRepository.findAll());
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
