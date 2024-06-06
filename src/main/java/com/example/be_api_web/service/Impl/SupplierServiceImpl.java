package com.example.be_api_web.service.Impl;

import com.example.be_api_web.controller.message.Message;
import com.example.be_api_web.entity.product.Material;
import com.example.be_api_web.entity.product.Supplier;
import com.example.be_api_web.repository.product.SupplierRepository;
import com.example.be_api_web.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    private boolean isValid(String str) {
        return str.matches("^[a-zA-Z\\d\\s\\S]+$");
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public ResponseEntity<Supplier> editSupplier(Supplier updateSupplier) {
        Supplier optional=supplierRepository.findByName(updateSupplier.getName());
        String errorMessage;
        Message errorResponse;

        if (optional !=null){
            errorMessage = " Trùng Supplier";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(updateSupplier.getName()==null || !isValid(updateSupplier.getName())){
            errorMessage="Material Không Hợp Lệ";
            errorResponse =new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Supplier> optionalSupplier=supplierRepository.findById(updateSupplier.getId());
            if(optionalSupplier.isPresent()){
                Supplier supplier=optionalSupplier.get();
                supplier.setName(updateSupplier.getName());
                supplier.setAddress(updateSupplier.getAddress());
                supplierRepository.save(supplier);
                return ResponseEntity.ok().body(supplier);
            }
            else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> saveSupplier(Supplier saveSupplier) {
        Supplier optional=supplierRepository.findByName(saveSupplier.getName());
        String errorMessages;
        Message errorResponse;

        if(optional !=null){
            errorMessages="Trùng Nhà Cúng Cấp";
            errorResponse=new Message(errorMessages, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(saveSupplier.getName()==null || !isValid(saveSupplier.getName())){
            errorMessages="Nhà Cung Cấp Không Hợp Lệ";
            errorResponse=new Message(errorMessages, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        try {
            Supplier supplier=new Supplier();
            supplier.setName(saveSupplier.getName());
            supplierRepository.save(supplier);
            return ResponseEntity.ok().body(supplierRepository.findAll());
        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Supplier>> deleteSupplier(Long id) {
        try {
            Optional<Supplier> optionalSupplier=supplierRepository.findById(id);
            if(optionalSupplier.isPresent()){
                Supplier supplier=optionalSupplier.get();
                supplier.setDeleted(true);
                supplierRepository.save(supplier);
                return ResponseEntity.ok().body(supplierRepository.findAll());
            }
            else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public List<Supplier> searchSupplier(Long id) {
        return (List<Supplier>) supplierRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Supplier Không tồn Tại"));
    }
}
