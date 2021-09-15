package com.example.vssemployee.service.impl;

import com.example.vssemployee.entity.Project;
import com.example.vssemployee.repository.ProjectRepository;
import com.example.vssemployee.service.IProjectService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 12:02 PM
 */
@Service
public class ProjectServiceImpl implements IProjectService {
    private static final Logger logger = Logger.getLogger(ProjectServiceImpl.class);
    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Integer id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public List<Project> getProjectByName(String name) {
        return projectRepository.findByName(name);
    }

    @Override
    public Project created(Project project) {
        Project projects = projectRepository.save(project);
        return projects;
    }

    @Override
    public Project update(Project project) {
        Project projects = projectRepository.save(project);
        return projects;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Project> getProjectByCustomerId(Integer customerId) {
        return projectRepository.findByCustomerId(customerId);
    }
}
