package com.example.vssemployee.service.impl;

import com.example.vssemployee.entity.EmployeeExpectedSkill;
import com.example.vssemployee.entity.EmployeeSkill;
import com.example.vssemployee.repository.EmployeeExpectedSkillRepository;
import com.example.vssemployee.service.IEmployeeExpectedSkillService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 4:50 PM
 */

@Service
public class EmployeeExpectedSkillServiceImpl implements IEmployeeExpectedSkillService {
    private final static Logger logger = Logger.getLogger(EmployeeExpectedSkillServiceImpl.class);
    private EmployeeExpectedSkillRepository employeeExpectedSkillRepository;

    public EmployeeExpectedSkillServiceImpl(EmployeeExpectedSkillRepository employeeExpectedSkillRepository) {
        this.employeeExpectedSkillRepository = employeeExpectedSkillRepository;
    }

    @Override
    public List<EmployeeExpectedSkill> getAlls() {
        return employeeExpectedSkillRepository.findAll();
    }

    @Override
    public EmployeeExpectedSkill getById(Integer id) {
        return employeeExpectedSkillRepository.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeExpectedSkill> getByEmployeeId(Integer id) {
        return employeeExpectedSkillRepository.findByEmployeeId(id);
    }

    @Override
    public EmployeeExpectedSkill update(EmployeeExpectedSkill employeeExpectedSkill) {
        EmployeeExpectedSkill employeeExpectedSkills = employeeExpectedSkillRepository.save(employeeExpectedSkill);
        return employeeExpectedSkills;
    }

    @Override
    public EmployeeExpectedSkill create(EmployeeExpectedSkill employeeExpectedSkill) {
        EmployeeExpectedSkill employeeExpectedSkills = employeeExpectedSkillRepository.save(employeeExpectedSkill);
        return employeeExpectedSkills;
    }

    @Override
    public void deleteById(Integer id) {
        employeeExpectedSkillRepository.deleteById(id);
    }

    @Override
    public Integer deleteByEmployeeId(Integer employeeId) {
        return employeeExpectedSkillRepository.deleteByEmployeeId(employeeId);
    }

    @Override
    public void deleteAll() {
        employeeExpectedSkillRepository.deleteAll();
    }
}
