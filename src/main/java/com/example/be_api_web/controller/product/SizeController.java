package com.example.be_api_web.controller.product;

import com.example.be_api_web.entity.product.Size;
import com.example.be_api_web.service.SizeSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/size")
public class SizeController {
    @Autowired
    private SizeSerice sizeSerice;
    @GetMapping("/get-all")
    public ResponseEntity<List<Size>> getAll(){
        return ResponseEntity.ok().body(sizeSerice.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?>createSize(@RequestBody Size size){
        return ResponseEntity.ok().body(sizeSerice.saveSize(size));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Size>updateSize(@RequestBody Size size){
        return sizeSerice.editSize(size);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Size>>deleteSize(@PathVariable("id") Long id){
        return sizeSerice.deleteSize(id);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<List<Size>>searchSize(@PathVariable @Positive Long id){
        return ResponseEntity.ok().body(sizeSerice.searchSize(id));
    }
}
