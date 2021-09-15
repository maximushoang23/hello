package com.example.vssemployee.service.impl;

import com.example.vssemployee.entity.Employee;
import com.example.vssemployee.entity.EmployeeRole;
import com.example.vssemployee.repository.EmployeeRoleRepository;
import com.example.vssemployee.service.IEmployeeRoleService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 4:50 PM
 */

@Service
public class EmployeeRoleServiceImpl implements IEmployeeRoleService {
    private final static Logger logger = Logger.getLogger(EmployeeRoleServiceImpl.class);
    private EmployeeRoleRepository employeeRoleRepository;

    public EmployeeRoleServiceImpl(EmployeeRoleRepository employeeRoleRepository) {
        this.employeeRoleRepository = employeeRoleRepository;
    }

    @Override
    public List<EmployeeRole> getAlls() {
        return employeeRoleRepository.findAll();
    }

    @Override
    public EmployeeRole getById(Integer id) {
        return employeeRoleRepository.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeRole> getByEmployeeId(Integer id) {
        return employeeRoleRepository.findByEmployeeId(id);
    }

    @Override
    public EmployeeRole update(EmployeeRole employeeRole) {
        EmployeeRole employeeRoles = employeeRoleRepository.save(employeeRole);
        return employeeRoles;
    }

    @Override
    public EmployeeRole create(EmployeeRole employeeRole) {
        EmployeeRole employeeRoles = employeeRoleRepository.save(employeeRole);
        return employeeRoles;
    }

    @Override
    public void deleteById(Integer id) {
        employeeRoleRepository.deleteById(id);
    }

    @Override
    public Integer deleteByEmployeeId(Integer employeeId) {
        return employeeRoleRepository.deleteByEmployeeId(employeeId);
    }

    @Override
    public void deleteAll() {
        employeeRoleRepository.deleteAll();
    }
}
