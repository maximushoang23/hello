package com.example.vssemployee.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author manhdt14
 * created in 9/6/2021 9:06 AM
 */

@Repository
public class SkillRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    String sql;
    public void resetAutoIncrement() {
//        sql = "alter sequence m_skill_id_seq restart with 1";
//        entityManager.createQuery(sql).executeUpdate();
    }

}
