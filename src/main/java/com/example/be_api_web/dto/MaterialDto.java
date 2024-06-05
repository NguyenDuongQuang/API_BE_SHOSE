package com.example.be_api_web.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MaterialDto {
    private Long id;

    private String material;

    private Date createdDate;

    private String createdby;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    private boolean isDeleted;
}
