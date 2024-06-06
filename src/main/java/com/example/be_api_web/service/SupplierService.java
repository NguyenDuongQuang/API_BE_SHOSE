package com.example.be_api_web.service;

import com.example.be_api_web.entity.product.Supplier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierService {
    List<Supplier> findAll();

    ResponseEntity<Supplier> editSupplier(Supplier updateSupplier);

    ResponseEntity<?>saveSupplier(Supplier saveSupplier);

    ResponseEntity<List<Supplier>>deleteSupplier(Long id);

    List<Supplier>searchSupplier(Long id);
}
