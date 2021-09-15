package com.example.vssemployee.repository;

import com.example.vssemployee.entity.RequirementProjectSkill;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @author manhdt14
 * created in 9/7/2021 11:27 AM
 */
public class RequirementProjectSkillRepositoryCustom {
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("RequirementProjectSkillRepositoryCustom");
//    EntityManager em = emf.createEntityManager();
//
//    List<RequirementProjectSkill> findByProjectId() {
//        Query query = em.createQuery("select m_requirement_project_skill.id, m_requirement_project_skill.requirement_project_id, m_requirement_project_skill.skill_id, m_requirement_project_skill.level_id, m_requirement_project_skill.combination_skill_id\n" +
//                "from m_requirement_project_skill, m_requirement_project, m_project where m_requirement_project_skill.requirement_project_id = m_requirement_project.id and m_requirement_project.project_id = m_project.id and m_project.id = 1;")
//    }
}
