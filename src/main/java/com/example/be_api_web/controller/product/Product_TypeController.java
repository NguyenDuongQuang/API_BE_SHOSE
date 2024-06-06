package com.example.be_api_web.controller.product;

import com.example.be_api_web.entity.product.Product_Type;
import com.example.be_api_web.service.Product_TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/product_type")
public class Product_TypeController {

    @Autowired
    private Product_TypeService product_typeService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Product_Type>> getAll(){
        return ResponseEntity.ok().body(product_typeService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?>createPro_Type(@RequestBody Product_Type product_type){
        return ResponseEntity.ok().body(product_typeService.savePro_Type(product_type));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product_Type>updatePro_Type(@RequestBody Product_Type product_type){
        return product_typeService.editPro_Type(product_type);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Product_Type>>deletePro_Type(@PathVariable("id") Long id){
        return product_typeService.deletePro_Type(id);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<List<Product_Type>>searchPro_Type(@PathVariable @Positive Long id){
        return ResponseEntity.ok().body(product_typeService.searchPro_Type(id));
    }
}
