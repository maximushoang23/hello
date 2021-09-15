package com.example.vssemployee.repository;

import com.example.vssemployee.entity.RequirementProjectSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 2:15 PM
 */

@Repository
public interface RequirementProjectSkillRepository extends JpaRepository<RequirementProjectSkill, Integer> {
//    String sql = "select m_requirement_project_skill.id, m_requirement_project_skill.requirement_project_id, m_requirement_project_skill.skill_id, m_requirement_project_skill.level_id, m_requirement_project_skill.combination_skill_id\n" +
//            "from m_requirement_project_skill, m_requirement_project, m_project where m_requirement_project_skill.requirement_project_id = m_requirement_project.id and m_requirement_project.project_id = m_project.id and m_project.id := projectId";
//    @Query(value = sql)
//    List<RequirementProjectSkill> findByProjectId(@Param(value = "projectId") Integer projectId);
//
//    @Query(value = "select distinct * from RequirementProjectSkill")
//    List<RequirementProjectSkill> findByProjectId();

    @Query(value = "select mrps.id, mrps.requirement_project_id, mrps.skill_id, mrps.level_id, mrps.combination_skill_id\n " +
            "from m_requirement_project_skill mrps, m_requirement_project mrp, m_project mp\n " +
            "where mrps.requirement_project_id = mrp.id and mp.id = mrp.project_id\n " +
            "and mp.id =?1", nativeQuery = true)
    List<RequirementProjectSkill> getAllRequirementSkillOfAProject(Integer projectId);

    RequirementProjectSkill findByCombinationSkillId(@Param(value = "id") Integer id);
}
