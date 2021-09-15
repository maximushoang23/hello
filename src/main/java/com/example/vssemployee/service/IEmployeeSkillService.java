package com.example.vssemployee.service;

import com.example.vssemployee.entity.EmployeeRole;
import com.example.vssemployee.entity.EmployeeSkill;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 4:48 PM
 */
public interface IEmployeeSkillService {
    List<EmployeeSkill> getAlls();

    EmployeeSkill getById(Integer id);

    List<EmployeeSkill>  getByEmployeeId(Integer id);

    EmployeeSkill update(EmployeeSkill employeeSkill);

    EmployeeSkill create(EmployeeSkill employeeSkill);

    void deleteById(Integer id);

    Integer deleteByEmployeeId(Integer employeeId);

    void deleteAll();
}
