package com.example.vssemployee.repository;

import com.example.vssemployee.entity.EmployeeRole;
import com.example.vssemployee.entity.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 4:45 PM
 */

@Repository
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Integer> {
    List<EmployeeSkill> findByEmployeeId(@Param(value = "employeeId") Integer employeeId);

    @Transactional
    Integer deleteByEmployeeId(@Param(value = "employeeId") Integer employeeId);

    List<EmployeeSkill> findBySkillIdAndLevelId(@Param(value = "skillId") Integer skillId, @Param(value = "levelId") Integer levelId);
}
