package com.example.vssemployee.service;

import com.example.vssemployee.entity.Department;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 9:30 AM
 */
public interface IDepartmentService {
    List<Department> getAllDepartments();

    Department getDepartmentById(Integer id);

    List<Department> getDepartmentByName(String name);

    Department create(Department department);

    Department update(Department department);

    void deleteById(Integer id);

    void deleteAll();
}
