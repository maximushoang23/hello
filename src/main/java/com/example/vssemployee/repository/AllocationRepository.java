package com.example.vssemployee.repository;

import com.example.vssemployee.entity.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 2:33 PM
 */
@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Integer> {
    List<Allocation> findByCombinationSkillId(@Param(value = "id") Integer id);

    @Query(value = "select distinct ma.id, ma.combination_skill_id, ma.employee_id, ma.joining_date, ma.finish_date, ma.work_load \n" +
            "from m_allocation ma, m_requirement_project_skill mrps, m_requirement_project mrp, m_project mp \n" +
            "where ma.combination_skill_id = mrps.combination_skill_id and mrp.id = mrps.requirement_project_id \n" +
            "and mp.id = mrp.project_id and mp.id = 2;", nativeQuery = true)
    List<Allocation> getAllByProjectId(Integer projectId);
}
