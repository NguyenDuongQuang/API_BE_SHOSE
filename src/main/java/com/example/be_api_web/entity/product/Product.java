package com.example.be_api_web.entity.product;

import com.example.be_api_web.entity.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product extends Base implements Serializable {
    @Column(name = "name", columnDefinition = "nvarchar(256) null")
    private String name;

    @Column(name = "note", columnDefinition = "nvarchar(256) null")
    private String note;

    @Column(name = "price", columnDefinition = "nvarchar(256) null")
    private Float price;

    @Column(name = "status", columnDefinition = "int null")
    private Integer status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Material material;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_type_id", referencedColumnName = "id")
    private Product_Type product_type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;
}
