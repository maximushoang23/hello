package com.example.vssemployee.repository;

import com.example.vssemployee.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 9:32 AM
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findByName(@Param(value = "name") String name);
}
