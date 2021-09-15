package com.example.vssemployee.service.impl;

import com.example.vssemployee.entity.RequirementProject;
import com.example.vssemployee.repository.RequirementProjectRepository;
import com.example.vssemployee.service.IRequirementProjectService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 1:53 PM
 */
@Service
public class RequirementProjectServiceImpl implements IRequirementProjectService {
    private final static Logger logger = Logger.getLogger(RequirementProjectServiceImpl.class);
    private RequirementProjectRepository requirementProjectRepository;

    public RequirementProjectServiceImpl(RequirementProjectRepository requirementProjectRepository) {
        this.requirementProjectRepository = requirementProjectRepository;
    }

    @Override
    public RequirementProject getRequirementProjectById(Integer id) {
        return requirementProjectRepository.findById(id).orElse(null);
    }

    @Override
    public RequirementProject updateRequirementProject(RequirementProject requirementProject) {
        RequirementProject requirementProjects = requirementProjectRepository.save(requirementProject);
        return requirementProjects;
    }

    @Override
    public RequirementProject create(RequirementProject requirementProject) {
        RequirementProject requirementProjects = requirementProjectRepository.save(requirementProject);
        return requirementProjects;
    }

    @Override
    public List<RequirementProject> getAllRequirementProjectOfAProject(Integer projectId) {
        return requirementProjectRepository.findByProjectId(projectId);
    }

    @Override
    public List<RequirementProject> getAllRequirementProjects() {
        return requirementProjectRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        requirementProjectRepository.deleteById(id);
    }

    @Override
    public Integer deleteByProjectIdIs(Integer projectId) {
        return requirementProjectRepository.deleteByProjectId(projectId);
    }

    @Override
    public void deleteAll() {
        requirementProjectRepository.deleteAll();
    }
}
