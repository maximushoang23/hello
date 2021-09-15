package com.example.vssemployee.service.impl;

import com.example.vssemployee.entity.Department;
import com.example.vssemployee.repository.DepartmentRepository;
import com.example.vssemployee.service.IDepartmentService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 9:31 AM
 */

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    private final static Logger logger = Logger.getLogger(DepartmentServiceImpl.class);
    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Department> getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Override
    public Department create(Department department) {
        Department departments = departmentRepository.save(department);
        return departments;
    }

    @Override
    public Department update(Department department) {
        Department departments = departmentRepository.save(department);
        return departments;
    }

    @Override
    public void deleteById(Integer id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        departmentRepository.deleteAll();
    }
}
