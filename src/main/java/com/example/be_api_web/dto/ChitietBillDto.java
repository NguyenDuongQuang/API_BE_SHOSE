package com.example.be_api_web.dto;

import com.example.be_api_web.entity.bill.Bill;
import com.example.be_api_web.entity.bill.Bill_Detail;
import com.example.be_api_web.entity.bill.Bill_History;
import lombok.Data;

import java.util.List;

@Data
public class ChitietBillDto {
    List<Bill_Detail> bill_details;
    List<Bill_History> bill_histories;
    Bill bill;
}
