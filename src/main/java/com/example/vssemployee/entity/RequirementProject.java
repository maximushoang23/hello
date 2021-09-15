package com.example.vssemployee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author manhdt14
 * created in 8/31/2021 1:49 PM
 */

@Entity
@Data
@Table(name = "m_requirement_project")
@AllArgsConstructor
@NoArgsConstructor
public class RequirementProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "level_id")
    private Integer levelId;

}
