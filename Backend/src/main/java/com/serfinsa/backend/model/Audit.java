package com.serfinsa.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audit {

    @CreatedDate
    @Column(name="create_at", updatable = false)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name="update_at")
    private LocalDateTime updateAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private Integer createdBy;

    @LastModifiedBy
    @Column(name = "update_by")
    private Integer updateBy;
}
