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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "image")
@EntityListeners(AuditingEntityListener.class)
public class Image extends Base implements Serializable {
    @Column(name = "image_default",columnDefinition = "bit")
    private Boolean imagesDefault;

    @Column(name = "name",columnDefinition = "nvarchar(256) null")
    private String nameImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_color",referencedColumnName = "id")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product",referencedColumnName = "id")
    private Product product;
}

