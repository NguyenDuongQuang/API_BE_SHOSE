package com.example.be_api_web.entity.voucher;

import com.example.be_api_web.entity.Base;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Voucher extends Base implements Serializable {
    @Column(name = "name", columnDefinition = "nvarchar(256) not null")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "begin_date", columnDefinition = "Datetime null")
    private Date begin_date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "end_date", columnDefinition = "Datetime null")
    private Date end_date;

    @Column(name = "phan_tram_giam", columnDefinition = "int null")
    private int phanTramGiam;

    @Column(name = "money_max", columnDefinition = "int null")
    private int moneyMmax;

    @Column(name = "status", columnDefinition = "int null")
    private int status;
}
