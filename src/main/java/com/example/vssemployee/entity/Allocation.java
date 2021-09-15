package com.example.vssemployee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author manhdt14
 * created in 8/31/2021 2:29 PM
 */

@Entity
@Data
@Table(name = "m_allocation")
@AllArgsConstructor
@NoArgsConstructor
public class Allocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "combination_skill_id")
    private Integer combinationSkillId;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "joining_date")
    private Timestamp joiningDate;

    @Column(name = "finish_date")
    private Timestamp finishDate;

    @Column(name = "work_load")
    private Float workLoad;
}
