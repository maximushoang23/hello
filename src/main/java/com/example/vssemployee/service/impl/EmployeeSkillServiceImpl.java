package com.example.vssemployee.service.impl;

import com.example.vssemployee.entity.EmployeeSkill;
import com.example.vssemployee.repository.EmployeeSkillRepository;
import com.example.vssemployee.service.IEmployeeSkillService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 4:50 PM
 */

@Service
public class EmployeeSkillServiceImpl implements IEmployeeSkillService {
    private final static Logger logger = Logger.getLogger(EmployeeSkillServiceImpl.class);
    private EmployeeSkillRepository employeeSkillRepository;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
    }

    @Override
    public List<EmployeeSkill> getAlls() {
        return employeeSkillRepository.findAll();
    }

    @Override
    public EmployeeSkill getById(Integer id) {
        return employeeSkillRepository.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeSkill> getByEmployeeId(Integer id) {
        return employeeSkillRepository.findByEmployeeId(id);
    }

    @Override
    public EmployeeSkill update(EmployeeSkill employeeSkill) {
        EmployeeSkill employeeSkills = employeeSkillRepository.save(employeeSkill);
        return employeeSkills;
    }

    @Override
    public EmployeeSkill create(EmployeeSkill employeeSkill) {
        EmployeeSkill employeeSkills = employeeSkillRepository.save(employeeSkill);
        return employeeSkills;
    }

    @Override
    public void deleteById(Integer id) {
        employeeSkillRepository.deleteById(id);
    }

    @Override
    public Integer deleteByEmployeeId(Integer employeeId) {
        return employeeSkillRepository.deleteByEmployeeId(employeeId);
    }

    @Override
    public void deleteAll() {
        employeeSkillRepository.deleteAll();
    }
}
