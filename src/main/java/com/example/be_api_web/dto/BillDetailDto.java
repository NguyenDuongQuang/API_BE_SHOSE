package com.example.be_api_web.dto;

import com.example.be_api_web.entity.bill.Bill;
import com.example.be_api_web.entity.product.Product_Detail;
import lombok.Data;

@Data
public class BillDetailDto {
    private Long id;

    private Long idProduct;

    private Long idColor;

    private Long idSize;

    private String product;

    private Integer quantity;

    private Integer soLuong;

    private Integer price;

    private Integer total_money;

    private Bill bill;

    private Product_Detail product_detail;

    private String image;

    private int quantity_update;

    private String email_user;
}
