package com.example.be_api_web.entity.bill;

import com.example.be_api_web.entity.Base;
import com.example.be_api_web.entity.product.Product_Detail;
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
@Table(name = "bill_detail")
public class Bill_Detail extends Base implements Serializable {
    @Column(name = "quantity", columnDefinition = "int null")
    private int quantity;

    @Column(name = "price", columnDefinition = "int null")
    private int price;

    @Column(name = "total_money", columnDefinition = "int null")
    private int total_money;

    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "product_detail_id", referencedColumnName = "id")
    private Product_Detail product_detail;
}
