package com.example.be_api_web.service.Impl;

import com.example.be_api_web.controller.message.Message;
import com.example.be_api_web.entity.product.Product_Type;
import com.example.be_api_web.entity.product.Size;
import com.example.be_api_web.entity.product.Supplier;
import com.example.be_api_web.repository.product.Product_TypeRepository;
import com.example.be_api_web.service.Product_TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class Product_TypeServiceImpl implements Product_TypeService {
    @Autowired
    private Product_TypeRepository product_typeRepository;

    private boolean isValid(String str) {
        return str.matches("^[a-zA-Z\\d\\s\\S]+$");
    }

    @Override
    public List<Product_Type> findAll() {
        return product_typeRepository.findAll();
    }

    @Override
    public ResponseEntity<Product_Type> editPro_Type(Product_Type updatePro_Type) {
        Product_Type optional=product_typeRepository.findByName(updatePro_Type.getName());
        String errorMessage;
        Message errorResponse;

        if (optional !=null){
            errorMessage = " Trùng Loại Sản Phẩm";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(updatePro_Type.getName()==null || !isValid(updatePro_Type.getName())){
            errorMessage="Size Không Hợp Lệ";
            errorResponse =new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Product_Type> optionalProduct_type=product_typeRepository.findById(updatePro_Type.getId());
            if(optionalProduct_type.isPresent()){
                Product_Type product_type=optionalProduct_type.get();
                product_type.setName(updatePro_Type.getName());
                product_typeRepository.save(product_type);
                return ResponseEntity.ok().body(product_type);
            }
            else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> savePro_Type(Product_Type savePro_Type) {
        Product_Type optional=product_typeRepository.findByName(savePro_Type.getName());
        String errorMessages;
        Message errorResponse;

        if(optional !=null){
            errorMessages="Trùng Loại Sản Phẩm";
            errorResponse=new Message(errorMessages, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(savePro_Type.getName()==null || !isValid(savePro_Type.getName())){
            errorMessages="Loại Sản Phẩm Không Hợp Lệ";
            errorResponse=new Message(errorMessages, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        try {
            Product_Type product_type=new Product_Type();
            product_type.setName(savePro_Type.getName());
            product_typeRepository.save(product_type);
            return ResponseEntity.ok().body(product_typeRepository.findAll());
        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Product_Type>> deletePro_Type(Long id) {
        try {
            Optional<Product_Type> optionalProduct_type=product_typeRepository.findById(id);
            if(optionalProduct_type.isPresent()){
                Product_Type product_type=optionalProduct_type.get();
                product_type.setDeleted(true);
                product_typeRepository.save(product_type);
                return ResponseEntity.ok().body(product_typeRepository.findAll());
            }
            else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public List<Product_Type> searchPro_Type(Long id) {
        return (List<Product_Type>) product_typeRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Loại Sản Phẩm Không tồn Tại"));
    }
}
