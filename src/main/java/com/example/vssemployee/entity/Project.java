package com.example.vssemployee.entity;

import com.example.vssemployee.model.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author manhdt14
 * created in 8/31/2021 11:54 AM
 */

@Entity
@Table(name = "m_project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project extends Auditable<String> {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "started_at")
    private Timestamp startedAt;

    @Column(name = "ended_at")
    private Timestamp endedAt;

    @Column(name = "effort")
    private Integer effort;

    @Column(name = "status")
    private Integer status;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

}
