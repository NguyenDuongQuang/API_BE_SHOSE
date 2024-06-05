package com.example.be_api_web.dto;

import lombok.Data;

@Data
public class CartDto {
    private String code_color;

    private String size;

    private String email;

    private long product_id;

    private int quantity;

    private int price;

    private long [] id_cartDetail;

    private int total_money;

    private Integer quantity_begin;
}
