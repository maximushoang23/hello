package com.example.vssemployee.service;

import com.example.vssemployee.entity.EmployeeExpectedSkill;
import com.example.vssemployee.entity.EmployeeSkill;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 4:49 PM
 */
public interface IEmployeeExpectedSkillService {
    List<EmployeeExpectedSkill> getAlls();

    EmployeeExpectedSkill getById(Integer id);

    List<EmployeeExpectedSkill>  getByEmployeeId(Integer id);

    EmployeeExpectedSkill update(EmployeeExpectedSkill employeeExpectedSkill);

    EmployeeExpectedSkill create(EmployeeExpectedSkill employeeExpectedSkill);

    void deleteById(Integer id);

    Integer deleteByEmployeeId(Integer employeeId);

    void deleteAll();
}
