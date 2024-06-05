package com.example.be_api_web.entity.bill;

import com.example.be_api_web.entity.Base;
import com.example.be_api_web.entity.customer.Customer;
import com.example.be_api_web.entity.staff.Staff;
import com.example.be_api_web.entity.voucher.Voucher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bill")
public class Bill extends Base implements Serializable {
    @Column(name = "code_bill", columnDefinition = "nvarchar(256) null")
    private String code_bill;

    @Column(name = "note", columnDefinition = "nvarchar(50) null")
    private String note;

    @Column(name = "person", columnDefinition = "nvarchar(256) null")
    private String person;

    @Column(name = "email", columnDefinition = "nvarchar(256) null")
    private String email;

    @Column(name = "phone", columnDefinition = "nvarchar(50) null")
    private String phone;

    @Column(name = "address", columnDefinition = "nvarchar(256) null")
    private String address;

    @Column(name = "money_ship", columnDefinition = "int null")
    private int money_ship;

    @Column(name = "money_reduce", columnDefinition = "int null")
    private int money_reduce;

    @Column(name = "total_order", columnDefinition = "int null")
    private int total_order;

    @Column(name = "total_bill", columnDefinition = "int null")
    private int total_bill;

    @Column(name = "bill_type", columnDefinition = "int null")
    private int bill_type;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "voucher_id", referencedColumnName = "id")
    private Voucher voucher;
}
