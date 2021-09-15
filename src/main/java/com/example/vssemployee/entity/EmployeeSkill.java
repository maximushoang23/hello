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
@Table(name = "m_employee_skills")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "skill_id")
    private Integer skillId;

    @Column(name = "interest")
    private Integer interest;

    @Column(name = "certificate")
    private Boolean certificate;

    @Column(name = "core_competence")
    private Boolean coreCompetence;

    @Column(name = "level_id")
    private Integer levelId;
}
