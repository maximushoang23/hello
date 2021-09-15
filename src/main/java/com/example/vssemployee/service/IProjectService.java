package com.example.vssemployee.service;

import com.example.vssemployee.entity.Project;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 12:02 PM
 */
public interface IProjectService {
    List<Project> getAllProjects();

    Project getProjectById(Integer id);

    List<Project> getProjectByName(String name);

    Project created(Project project);

    Project update(Project project);

    void deleteById(Integer id);

    void deleteAll();
    List<Project> getProjectByCustomerId(Integer customerId);
}
