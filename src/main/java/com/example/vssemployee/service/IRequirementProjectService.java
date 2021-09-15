package com.example.vssemployee.service;

import com.example.vssemployee.entity.RequirementProject;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 1:52 PM
 */
public interface IRequirementProjectService {
    List<RequirementProject> getAllRequirementProjects();

    RequirementProject getRequirementProjectById(Integer id);

    List<RequirementProject> getAllRequirementProjectOfAProject(Integer projectId);

    RequirementProject updateRequirementProject(RequirementProject requirementProject);

    RequirementProject create(RequirementProject requirementProject);

    void deleteById(Integer id);

    Integer deleteByProjectIdIs(Integer projectId);

    void deleteAll();
}
