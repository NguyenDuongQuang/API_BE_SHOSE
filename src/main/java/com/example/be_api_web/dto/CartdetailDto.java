package com.example.be_api_web.dto;

import com.example.be_api_web.entity.product.Product_Detail;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartdetailDto {
    private  long id;

    private Product_Detail product_detail;

    private Integer quantity;

    private Integer price;

    private BigDecimal total_money;

    private String image;

    private Integer quantity_begin;

    private Integer quantity_update;

    private String email_user;
}
