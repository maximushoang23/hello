package com.example.vssemployee.service.impl;

import com.example.vssemployee.entity.RequirementProjectSkill;
import com.example.vssemployee.repository.RequirementProjectSkillRepository;
import com.example.vssemployee.service.IRequirementProjectSkillService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 2:21 PM
 */

@Service
public class RequirementProjectSkillServiceImpl implements IRequirementProjectSkillService {
    private final static Logger logger = Logger.getLogger(RequirementProjectSkillServiceImpl.class);
    private RequirementProjectSkillRepository requirementProjectSkillRepository;

    public RequirementProjectSkillServiceImpl(RequirementProjectSkillRepository requirementProjectSkillRepository) {
        this.requirementProjectSkillRepository = requirementProjectSkillRepository;
    }

    @Override
    public List<RequirementProjectSkill> getAllRequirementProjectSkill() {
        return requirementProjectSkillRepository.findAll();
    }

    @Override
    public RequirementProjectSkill getRequirementProjectSkillById(Integer id) {
        return requirementProjectSkillRepository.findById(id).orElse(null);
    }

    @Override
    public List<RequirementProjectSkill> getAllRequirementProjectSkillOfAProject(Integer projectId) {
        return requirementProjectSkillRepository.getAllRequirementSkillOfAProject(projectId);
    }

    @Override
    public void deleteById(Integer id) {
        requirementProjectSkillRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        requirementProjectSkillRepository.deleteAll();
    }

    @Override
    public RequirementProjectSkill getRequirementProjectSkillByCombinationSkillId(Integer id) {
        return requirementProjectSkillRepository.findByCombinationSkillId(id);
    }

    @Override
    public RequirementProjectSkill update(RequirementProjectSkill requirementProjectSkill) {
        RequirementProjectSkill requirementProjectSkills = requirementProjectSkillRepository.save(requirementProjectSkill);
        return requirementProjectSkills;
    }

    @Override
    public RequirementProjectSkill create(RequirementProjectSkill requirementProjectSkill) {
        RequirementProjectSkill requirementProjectSkills = requirementProjectSkillRepository.save(requirementProjectSkill);
        return requirementProjectSkills;
    }
}
