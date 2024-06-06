package com.example.be_api_web.controller.product;

import com.example.be_api_web.entity.product.Supplier;
import com.example.be_api_web.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @GetMapping("/get-all")
    public ResponseEntity<List<Supplier>> getAll(){
        return ResponseEntity.ok().body(supplierService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?>createSupplier(@RequestBody Supplier supplier){
        return ResponseEntity.ok().body(supplierService.saveSupplier(supplier));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Supplier>updateSupplier(@RequestBody Supplier supplier){
        return supplierService.editSupplier(supplier);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Supplier>>deleteSupplier(@PathVariable("id") Long id){
        return supplierService.deleteSupplier(id);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<List<Supplier>>searchSupplier(@PathVariable @Positive Long id){
        return ResponseEntity.ok().body(supplierService.searchSupplier(id));
    }
}
