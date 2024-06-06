package com.example.be_api_web.service;

import com.example.be_api_web.entity.product.Material;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterialService {
    List<Material>findAll();

    ResponseEntity<Material>editMaterial(Material updateMaterial);

    ResponseEntity<?>saveMaterial(Material saveMaterial);

    ResponseEntity<List<Material>>deleteMaterial(Long id);

    List<Material>searchMaterial(Long id);
}
