package com.example.be_api_web.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VoucherDto {
    private long id;

    private String name;

    private Date startedDate;

    private Date endDate;

    private int percentDiscount;

    private int maximumvalue;

    private int status;

    private Date createdDate;

    private String createdby;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    private boolean isDeleted;
}
