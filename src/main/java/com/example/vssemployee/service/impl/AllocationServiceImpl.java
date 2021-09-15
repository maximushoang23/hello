package com.example.vssemployee.service.impl;

import com.example.vssemployee.entity.Allocation;
import com.example.vssemployee.entity.EmployeeSkill;
import com.example.vssemployee.repository.AllocationRepository;
import com.example.vssemployee.service.IAllocationService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 2:35 PM
 */
@Service
public class AllocationServiceImpl implements IAllocationService {
    private final static Logger logger = Logger.getLogger(AllocationServiceImpl.class);
    private AllocationRepository allocationRepository;

    public AllocationServiceImpl(AllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

    @Override
    public List<Allocation> getAlls() {
        return allocationRepository.findAll();
    }

    @Override
    public Allocation getById(Integer id) {
        return allocationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Allocation> getByCombinationSkill(Integer id) {
        return allocationRepository.findByCombinationSkillId(id);
    }

    @Override
    public Allocation update(Allocation allocation) {
        Allocation allocations = allocationRepository.save(allocation);
        return allocations;
    }

    @Override
    public Allocation create(Allocation allocation) {
        Allocation allocations = allocationRepository.save(allocation);
        return allocations;
    }

    @Override
    public List<Allocation> getAllByProjectId(Integer projectId) {
        return allocationRepository.getAllByProjectId(projectId);
    }

    @Override
    public void deleteById(Integer id) {
        allocationRepository.deleteById(id);
    }

    @Override
    public void deleteAlls() {
        allocationRepository.deleteAll();
    }
}
