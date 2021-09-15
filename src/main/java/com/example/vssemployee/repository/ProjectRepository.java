package com.example.vssemployee.repository;

import com.example.vssemployee.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 12:01 PM
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByName(@Param(value = "name") String name);
    List<Project> findByCustomerId(@Param(value = "custumerid") Integer customerId);
}
