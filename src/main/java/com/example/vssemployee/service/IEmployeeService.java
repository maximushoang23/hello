package com.example.vssemployee.service;

import com.example.vssemployee.entity.Employee;
import com.example.vssemployee.entity.Level;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 11:07 AM
 */
public interface IEmployeeService {
    List<Employee> getAlls();

    Employee getById(Integer id);

    List<Employee> getByName(String name);

    Employee create(Employee employee);

    Employee update(Employee employee);

    void deleteById(Integer id);

    void deleteAlls();
}
