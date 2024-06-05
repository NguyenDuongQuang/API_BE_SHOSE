package com.example.be_api_web.dto;

import lombok.Data;

import java.util.List;

@Data
public class BillDto {
    private Long id;

    private String code_bill;

    private String note;

    private String email;

    private String phone;

    private int money_ship;

    private int total_bill;

    private int total_order;

    private List<Long> id_bill;

    private String email_user;

    private String address;

    private String voucher;

    private String creator;

    private String last_modified_by;

    private int bill_type;

    private int money_thua;

    private int money_person;

    private long id_voucher;
}
