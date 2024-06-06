package com.example.be_api_web.controller.product;

import com.example.be_api_web.entity.product.Color;
import com.example.be_api_web.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/color")
public class ColorController {
    @Autowired
    private ColorService colorService;
    @GetMapping("/get-all")
    public ResponseEntity<List<Color>> getAll(){
        return ResponseEntity.ok().body(colorService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?>createColor(@RequestBody Color color){
        return ResponseEntity.ok().body(colorService.saveColor(color));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Color>updateColor(@RequestBody Color color){
        return colorService.editColor(color);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Color>>deleteColor(@PathVariable("id") Long id){
        return colorService.deleteColor(id);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<List<Color>>searchColor(@PathVariable @Positive Long id){
        return ResponseEntity.ok().body(colorService.searchColor(id));
    }
}
