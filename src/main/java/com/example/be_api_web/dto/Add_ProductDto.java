package com.example.be_api_web.dto;

import lombok.Data;

import java.util.List;

@Data
public class Add_ProductDto {
    private String name;
    private Float price;
    private long material_id;
    private long product_type_id;
    private long supplier_id;
    private List<String> size;
    private List<Integer> color;
    private int quantity;
    private int status;
}
