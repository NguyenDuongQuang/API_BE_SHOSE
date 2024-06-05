package com.example.be_api_web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AutoCloseable.class)
public abstract class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "createdby", updatable = false)
    @CreatedBy
    private String createdby;

    @Column(name = "last_modified_date", updatable = true)
    @LastModifiedDate
    private Date lastModifiedDate;

    @Column(name = "last_modified_by", updatable = true)
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(name = "is_deleted", columnDefinition = "Bit")
    private boolean isDeleted;
}