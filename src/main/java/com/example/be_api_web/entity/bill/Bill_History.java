package com.example.be_api_web.entity.bill;

import com.example.be_api_web.entity.Base;
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
@Table(name = "bill_history")
public class Bill_History extends Base implements Serializable {
    @Column(name = "thao_tac", columnDefinition = "nvarchar(256) not null")
    private String thaoTac;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @Column(name = "nguoi_thao_tac", columnDefinition = "nvarchar(256)  null")
    private String nguoiThaoTac;

    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    private Bill bill;
}
