package com.example.be_api_web.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StaffDto {
    private long id;

    private String name;

    private Date dateOfBirth;

    private Boolean gender;

    private String password;

    private String email;

    private String phoneNumber;

    private String address;

    private int status;

    private Date createdDate;

    private String createdby;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    private boolean isDeleted;
}
