package com.example.vssemployee.entity;

import com.example.vssemployee.model.Auditable;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author manhdt14
 * created in 8/31/2021 10:44 AM
 */
@Entity
@Data
@Table(name = "m_employee")
public class Employee extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "birthday")
    private Timestamp birthday;

    @Column(name = "location")
    private String location;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "name")
    private String name;

    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "marital_status")
    private Integer maritalStatus;

    @Column(name = "email")
    private String email;

    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "user_code")
    private String userCode;
}
