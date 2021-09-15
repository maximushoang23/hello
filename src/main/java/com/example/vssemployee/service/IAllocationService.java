package com.example.vssemployee.service;

import com.example.vssemployee.entity.Allocation;
import com.example.vssemployee.entity.EmployeeSkill;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 2:34 PM
 */
public interface IAllocationService {
    List<Allocation> getAlls();

    Allocation getById(Integer id);

    List<Allocation>  getByCombinationSkill(Integer id);

    Allocation update(Allocation allocation);

    Allocation create(Allocation allocation);

    List<Allocation> getAllByProjectId(Integer projectId);

    void deleteById(Integer id);

    void deleteAlls();
}
