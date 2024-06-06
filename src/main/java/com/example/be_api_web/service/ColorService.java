package com.example.be_api_web.service;

import com.example.be_api_web.entity.product.Color;
import com.example.be_api_web.entity.product.Material;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ColorService {
    List<Color> findAll();

    ResponseEntity<Color> editColor(Color updateColor);

    ResponseEntity<?>saveColor(Color saveColor);

    ResponseEntity<List<Color>>deleteColor(Long id);

    List<Color>searchColor(Long id);
}
