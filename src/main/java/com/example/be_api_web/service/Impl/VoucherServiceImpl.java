package com.example.be_api_web.service.Impl;

import com.example.be_api_web.entity.voucher.Voucher;
import com.example.be_api_web.repository.voucher.VoucherRepository;
import com.example.be_api_web.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;
    @Override
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    public ResponseEntity<Voucher> editVOucher(Voucher editVoucher) {
        return null;
    }

    @Override
    public ResponseEntity<?> saveVoucher(Voucher saveVoucher) {
        return null;
    }

    @Override
    public ResponseEntity<List<Voucher>> deleteVoucher(Long id) {
        return null;
    }
}
