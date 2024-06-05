package com.example.be_api_web.dto;

import com.example.be_api_web.entity.product.Material;
import com.example.be_api_web.entity.product.Product_Type;
import com.example.be_api_web.entity.product.Supplier;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductDto {
    private Long id;

    private Long id_ProductDetail;

    private String name_Product;

    private Float price;

    private String origin;

    private Integer status;

    private Date created_At;

    private String created_By;

    private Material marterial;

    private Product_Type product_type;

    private Supplier supplier;

    private List<Long> size;

    private List<Long> color;

    private Long  marterial_id;

    private Long  product_type_id;

    private Long  supplier_id;

    //Dùng để add to cart bên phía customer
    private String code_Color;

    private String sizeDaChon;

    private Long product_id;

    private int quantityDaChon;

    private int donGia;

    private int quantity;

    private int total_Amount;

    private Long id_bill;

    private Integer quantityHienCo;

    private Long image_id;

    private String image_product;

    private Long color_id;

    private Long size_id;

    private String email_user;
}
