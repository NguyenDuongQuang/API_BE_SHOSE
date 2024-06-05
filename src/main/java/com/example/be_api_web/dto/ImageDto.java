package com.example.be_api_web.dto;

import lombok.Data;

import java.util.List;

@Data
public class ImageDto {
    private long id_images;

    private long id_ProductDetail;

    private List<String> nameImages;

    private Long id_Product;

    private String code_Color;
}
