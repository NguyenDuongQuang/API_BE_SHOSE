package com.example.be_api_web.service.Impl;

import com.example.be_api_web.controller.message.Message;
import com.example.be_api_web.entity.product.Material;
import com.example.be_api_web.repository.product.MaterialRepository;
import com.example.be_api_web.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    private boolean isValid(String str) {
        return str.matches("^[a-zA-Z\\d\\s\\S]+$");
    }

    @Override
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    @Override
    public ResponseEntity<Material> editMaterial(Material updateMaterial) {
        Material optional=materialRepository.findByName(updateMaterial.getName());
        String errorMessage;
        Message errorResponse;

        if (optional !=null){
            errorMessage = " Trùng loại chất liệu";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(updateMaterial.getName()==null || !isValid(updateMaterial.getName())){
            errorMessage="Material Không Hợp Lệ";
            errorResponse =new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Material>optionalMaterial=materialRepository.findById(updateMaterial.getId());
            if(optionalMaterial.isPresent()){
                Material material=optionalMaterial.get();
                material.setName(updateMaterial.getName());
                materialRepository.save(material);
                return ResponseEntity.ok().body(material);
            }
            else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> saveMaterial(Material saveMaterial) {
        Material optional=materialRepository.findByName(saveMaterial.getName());
        String errorMessages;
        Message errorResponse;

        if(optional !=null){
            errorMessages="Trùng Material";
            errorResponse=new Message(errorMessages, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(saveMaterial.getName()==null || !isValid(saveMaterial.getName())){
            errorMessages="Material Không Hợp Lệ";
            errorResponse=new Message(errorMessages, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        try {
            Material material=new Material();
            material.setName(saveMaterial.getName());
            materialRepository.save(material);
            return ResponseEntity.ok().body(materialRepository.findAll());
        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Material>> deleteMaterial(Long id) {
        try {
            Optional<Material> optionalMaterial=materialRepository.findById(id);
            if(optionalMaterial.isPresent()){
                Material material=optionalMaterial.get();
                material.setDeleted(true);
                materialRepository.save(material);
                return ResponseEntity.ok().body(materialRepository.findAll());
            }
            else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public List<Material> searchMaterial(Long id) {
        return (List<Material>) materialRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Material Không tồn Tại"));
    }
}
