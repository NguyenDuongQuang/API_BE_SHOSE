package com.example.be_api_web.entity.customer;

import com.example.be_api_web.entity.Base;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "address")
@Entity
public class Address extends Base implements Serializable {
    @Column(name = "address", columnDefinition = "nvarchar(500) null")
    private String address;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    @Column(name = "address_default")
    private boolean address_default = false;
}
