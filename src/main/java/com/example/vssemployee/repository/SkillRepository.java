package com.example.vssemployee.repository;

import com.example.vssemployee.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/30/2021 8:48 PM
 */

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    List<Skill> findByName(@Param(value = "name") String name);
}
