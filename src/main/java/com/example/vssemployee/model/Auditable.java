package com.example.vssemployee.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author manhdt14
 * created in 9/6/2021 11:52 AM
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class Auditable<U> {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;


}
