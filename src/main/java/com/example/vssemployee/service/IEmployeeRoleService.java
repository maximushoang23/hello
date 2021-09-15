package com.example.vssemployee.service;

import com.example.vssemployee.entity.Employee;
import com.example.vssemployee.entity.EmployeeRole;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 4:47 PM
 */
public interface IEmployeeRoleService {
    List<EmployeeRole> getAlls();

    EmployeeRole getById(Integer id);

    List<EmployeeRole>  getByEmployeeId(Integer id);

    EmployeeRole update(EmployeeRole employeeRole);

    EmployeeRole create(EmployeeRole employeeRole);

    void deleteById(Integer id);

    Integer deleteByEmployeeId(Integer employeeId);

    void deleteAll();
}
