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
@Table(name = "m_employee_expectedskill")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeExpectedSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "skill_id_expected")
    private Integer skillIdExpected;

    @Column(name = "level_id_expected")
    private Integer levelIdExpected;
}
