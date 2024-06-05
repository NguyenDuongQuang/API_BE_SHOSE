package com.example.be_api_web.entity.cart;

import com.example.be_api_web.entity.Base;
import com.example.be_api_web.entity.product.Product_Detail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cart_detail")
public class Cart_Detail extends Base implements Serializable {
    @ManyToOne
    @JoinColumn(name = "cart_id",referencedColumnName = "id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_detail_id",referencedColumnName = "id")
    private Product_Detail product_detail;

    @Column(name = "quantity", columnDefinition = "int null")
    private int quantity;

    @Column(name = "price", columnDefinition = "int ")
    private int price;

    @Column(name = "total_money", columnDefinition = "varchar(50) not null")
    private BigDecimal total_money;
}
