package com.example.vssemployee.service;

import com.example.vssemployee.entity.RequirementProject;
import com.example.vssemployee.entity.RequirementProjectSkill;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 2:20 PM
 */
public interface IRequirementProjectSkillService {
    List<RequirementProjectSkill> getAllRequirementProjectSkill();

    RequirementProjectSkill getRequirementProjectSkillById(Integer id);

    RequirementProjectSkill getRequirementProjectSkillByCombinationSkillId(Integer id);

    List<RequirementProjectSkill> getAllRequirementProjectSkillOfAProject(Integer projectId);

    RequirementProjectSkill update(RequirementProjectSkill requirementProjectSkill);

    RequirementProjectSkill create(RequirementProjectSkill requirementProjectSkill);

    void deleteById(Integer id);

    void deleteAll();
}
