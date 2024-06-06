package com.example.be_api_web.service;

import com.example.be_api_web.entity.product.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SizeSerice {
    List<Size> findAll();

    ResponseEntity<Size> editSize(Size updateSize);

    ResponseEntity<?>saveSize(Size saveSize);

    ResponseEntity<List<Size>>deleteSize(Long id);

    List<Size>searchSize(Long id);
}
