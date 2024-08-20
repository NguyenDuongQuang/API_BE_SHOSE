package com.example.be_api_web.service;

import com.example.be_api_web.entity.voucher.Voucher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoucherService  {
    List<Voucher>findAll() ;
    ResponseEntity<Voucher>editVOucher(Voucher editVoucher);
    ResponseEntity<?>saveVoucher(Voucher saveVoucher);
    ResponseEntity<List<Voucher>>deleteVoucher(Long id);
    
}
