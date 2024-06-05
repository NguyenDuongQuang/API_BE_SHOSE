package com.example.be_api_web.dto;

import lombok.Data;

@Data
public class SPCTDto {
    private Long productId;

    private Long spctId;

    private int quantity;

    private String code_color;

    private String size;
}

