package com.example.be_api_web.controller.product;

import com.example.be_api_web.entity.product.Material;
import com.example.be_api_web.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Material>>getAll(){
        return ResponseEntity.ok().body(materialService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?>createMaterial(@RequestBody Material material){
        return ResponseEntity.ok().body(materialService.saveMaterial(material));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Material>updateMaterial(@RequestBody Material material){
        return materialService.editMaterial(material);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Material>>deleteMaterial(@PathVariable("id") Long id){
       return materialService.deleteMaterial(id);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<List<Material>>searchMaterial(@PathVariable @Positive Long id){
        return ResponseEntity.ok().body(materialService.searchMaterial(id));
    }
}
