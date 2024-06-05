package com.example.be_api_web.dto;

import com.example.be_api_web.entity.product.Color;
import com.example.be_api_web.entity.product.Product;
import com.example.be_api_web.entity.product.Size;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDetailDto {
    private Long id;

    private Integer quantity;

    private boolean status;

    private Date createdDate;

    private String createdby;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    private Product product;

    private Color color;

    private Size size;

    private long color_id;

    private long size_id;

    private long product_id;
}
