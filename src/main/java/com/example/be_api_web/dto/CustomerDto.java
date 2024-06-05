package com.example.be_api_web.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {
    private Long id;

    private String name;

    private String phone;

    private long id_bill;

    private String email;

    private Date birth_day;

    private String address;
}
