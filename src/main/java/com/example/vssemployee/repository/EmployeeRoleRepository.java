package com.example.vssemployee.repository;

import com.example.vssemployee.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 4:44 PM
 */

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {
    List<EmployeeRole> findByEmployeeId(@Param(value = "employeeId") Integer employeeId);

    @Transactional
    Integer deleteByEmployeeId(@Param(value = "employeeId") Integer employeeId);

    List<EmployeeRole> findByRoleIdAndLevelId(@Param(value = "roleId") Integer roleId, @Param(value = "levelId") Integer levelId);
}
