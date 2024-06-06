package com.example.be_api_web.service.Impl;

import com.example.be_api_web.controller.message.Message;
import com.example.be_api_web.entity.product.Color;
import com.example.be_api_web.entity.product.Material;
import com.example.be_api_web.repository.product.ColorRepository;
import com.example.be_api_web.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    private boolean isValid(String str) {
        return str.matches("^[a-zA-Z\\d\\s\\S]+$");
    }

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public ResponseEntity<Color> editColor(Color updateColor) {
        Color optional=colorRepository.findByName(updateColor.getName());
        String errorMessage;
        Message errorResponse;

        if (optional !=null){
            errorMessage = " Trùng Màu Sắc";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(updateColor.getName()==null || !isValid(updateColor.getName())){
            errorMessage="Màu Sắc Không Hợp Lệ";
            errorResponse =new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Color> optionalColor=colorRepository.findById(updateColor.getId());
            if(optionalColor.isPresent()){
                Color color=optionalColor.get();
                color.setName(updateColor.getName());
                color.setCode_Color(updateColor.getCode_Color());
                colorRepository.save(color);
                return ResponseEntity.ok().body(color);
            }
            else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> saveColor(Color saveColor) {
        Color optional=colorRepository.findByName(saveColor.getName());
        String errorMessages;
        Message errorResponse;

        if(optional !=null){
            errorMessages="Trùng Màu Sắc";
            errorResponse=new Message(errorMessages, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(saveColor.getName()==null || !isValid(saveColor.getName())){
            errorMessages="Màu Sắc Không Hợp Lệ";
            errorResponse=new Message(errorMessages, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        try {
            Color color=new Color();
            color.setName(saveColor.getName());
            color.setCode_Color(saveColor.getCode_Color());
            colorRepository.save(color);
            return ResponseEntity.ok().body(colorRepository.findAll());
        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Color>> deleteColor(Long id) {
        try {
            Optional<Color> optionalColor=colorRepository.findById(id);
            if(optionalColor.isPresent()){
                Color color=optionalColor.get();
                color.setDeleted(true);
                colorRepository.save(color);
                return ResponseEntity.ok().body(colorRepository.findAll());
            }
            else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public List<Color> searchColor(Long id) {
        return (List<Color>) colorRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Color Không tồn Tại"));
    }
}
