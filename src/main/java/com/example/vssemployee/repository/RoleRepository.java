package com.example.vssemployee.repository;

import com.example.vssemployee.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 9:20 AM
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> findByName(@Param(value = "name") String name);
}
