package com.example.vssemployee.service.impl;

import com.example.vssemployee.entity.Employee;
import com.example.vssemployee.repository.EmployeeRepository;
import com.example.vssemployee.service.IEmployeeService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 11:08 AM
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAlls() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> getByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public Employee create(Employee employee) {
        Employee employees = employeeRepository.save(employee);
        return employees;
    }

    @Override
    public Employee update(Employee employee) {
        Employee employees = employeeRepository.save(employee);
        return employees;
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAlls() {
        employeeRepository.deleteAll();
    }
}
