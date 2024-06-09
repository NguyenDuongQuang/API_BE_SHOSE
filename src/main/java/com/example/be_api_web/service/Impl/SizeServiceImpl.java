package com.example.be_api_web.service.Impl;

import com.example.be_api_web.controller.message.Message;


import com.example.be_api_web.entity.product.Size;
import com.example.be_api_web.repository.product.SizeRepository;
import com.example.be_api_web.service.SizeSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeSerice {
    @Autowired
    private SizeRepository sizeRepository;

    private boolean isValid(String str) {
        return str.matches("^[a-zA-Z\\d\\s\\S]+$");
    }

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public ResponseEntity<Size> editSize(Size updateSize) {
        Size optional=sizeRepository.findByNameSize(updateSize.getNameSize());
        String errorMessage;
        Message errorResponse;

        if (optional !=null){
            errorMessage = " Trùng loại Size";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(updateSize.getNameSize()==null || !isValid(updateSize.getNameSize())){
            errorMessage="Size Không Hợp Lệ";
            errorResponse =new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Size> optionalSize=sizeRepository.findById(updateSize.getId());
            if(optionalSize.isPresent()){
                Size size=optionalSize.get();
                size.setNameSize(updateSize.getNameSize());
                sizeRepository.save(size);
                return ResponseEntity.ok().body(size);
            }
            else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> saveSize(Size saveSize) {
        Size optional=sizeRepository.findByNameSize(saveSize.getNameSize());
        String errorMessages;
        Message errorResponse;

        if(optional !=null){
            errorMessages="Trùng Size";
            errorResponse=new Message(errorMessages, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(saveSize.getNameSize()==null || !isValid(saveSize.getNameSize())){
            errorMessages="Material Không Hợp Lệ";
            errorResponse=new Message(errorMessages, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        try {
            Size size=new Size();
            size.setNameSize(saveSize.getNameSize());
            sizeRepository.save(size);
            return ResponseEntity.ok().body(sizeRepository.findAll());
        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Size>> deleteSize(Long id) {
        try {
            Optional<Size> optionalSize=sizeRepository.findById(id);
            if(optionalSize.isPresent()){
                Size size=optionalSize.get();
                size.setDeleted(true);
                sizeRepository.save(size);
                return ResponseEntity.ok().body(sizeRepository.findAll());
            }
            else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public List<Size> searchSize(Long id) {
        return (List<Size>) sizeRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Soze Không tồn Tại"));
    }
}
