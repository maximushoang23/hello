package com.example.vssemployee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author manhdt14
 * created in 8/31/2021 2:15 PM
 */

@Entity
@Data
@Table(name = "m_requirement_project_skill")
@AllArgsConstructor
@NoArgsConstructor
public class RequirementProjectSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "requirement_project_id")
    private Integer requirementProjectId;

    @Column(name = "Skill_id")
    private Integer skillId;

    @Column(name = "level_id")
    private Integer levelId;

    @Column(name = "combination_skill_id")
    private Integer combinationSkillId;
}
