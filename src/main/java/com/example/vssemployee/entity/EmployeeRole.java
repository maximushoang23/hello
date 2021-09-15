package com.example.vssemployee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author manhdt14
 * created in 8/31/2021 4:21 PM
 */
@Entity
@Data
@Table(name = "m_employee_role")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "level_id")
    private Integer levelId;
}
