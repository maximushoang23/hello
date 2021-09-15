package com.example.vssemployee.repository;

import com.example.vssemployee.entity.Employee;
import com.example.vssemployee.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 11:10 AM
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByName(@Param(value = "name") String name);

    @Query(value = "select me.id, me.birthday, me.location, me.updated_by, me.name, me.created_at, \n" +
            "me.updated_at, me.marital_status, me.email, me.department_id, me.user_code \n" +
            "from m_employee_skills mps, m_employee_role mpr, m_employee me, m_employee_expectedskill mpe\n" +
            "where mps.employee_id = mpr.employee_id and mpr.employee_id = me.id and mpe.employee_id = mps.employee_id\n" +
            "and mpr.role_id =?1 and mpr.level_id =?2 and mps.skill_id =?3 and mps.level_id =?4 and mpe.skill_id_expected=?3", nativeQuery = true)
    List<Employee> getEmployee(Integer roleId, Integer levelIdR,
                               Integer skilId, Integer levelIdS);
}
