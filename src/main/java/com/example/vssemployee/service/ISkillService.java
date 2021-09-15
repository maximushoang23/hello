package com.example.vssemployee.service;

import com.example.vssemployee.entity.Skill;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/30/2021 8:47 PM
 */

public interface ISkillService {
    List<Skill> getAllSkills();

    Skill getSkillById(Integer id);

    List<Skill> getSkillByName(String name);

    Skill created(Skill skill);

    Skill update(Skill skill);

    void deleteById(Integer id);

    void deleteAll();
}
