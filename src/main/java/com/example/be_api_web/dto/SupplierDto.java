package com.example.be_api_web.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SupplierDto {
    private long id;

    private String name;

    private String address;

    private Date createdDate;

    private String createdby;

    private Date lastModifiedDate;

    private String lastModifiedBy;
}
