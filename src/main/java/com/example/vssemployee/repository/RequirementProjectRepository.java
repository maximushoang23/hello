package com.example.vssemployee.repository;

import com.example.vssemployee.entity.RequirementProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 1:51 PM
 */
@Repository
public interface RequirementProjectRepository extends JpaRepository<RequirementProject, Integer> {
    List<RequirementProject> findByProjectId(@Param(value = "projectId") Integer id);

    @Transactional
    Integer deleteByProjectId(@Param(value = "projectId1") Integer projectId1);

}
