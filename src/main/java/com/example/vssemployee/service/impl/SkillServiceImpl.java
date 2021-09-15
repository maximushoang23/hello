package com.example.vssemployee.service.impl;

import com.example.vssemployee.entity.Skill;
import com.example.vssemployee.repository.SkillRepositoryCustom;
import com.example.vssemployee.service.ISkillService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.example.vssemployee.repository.SkillRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manhdt14
 * created in 8/30/2021 9:14 PM
 */

@Service
public class SkillServiceImpl implements ISkillService {
    private final static Logger logger = Logger.getLogger(SkillServiceImpl.class);
    private SkillRepository skillRepository;
    private SkillRepositoryCustom skillRepositoryCustom;

    public SkillServiceImpl(SkillRepository skillRepository, SkillRepositoryCustom skillRepositoryCustom) {
        this.skillRepository = skillRepository;
        this.skillRepositoryCustom = skillRepositoryCustom;
    }
    @Override
    public List<Skill> getAllSkills() {
        List<Skill> skills = new ArrayList<>();
        skillRepository.findAll().forEach(s -> skills.add(s));
        return skills;
    }

    @Override
    public Skill getSkillById(Integer id) {
        return skillRepository.findById(id).orElse(null);
    }

    @Override
    public List<Skill> getSkillByName(String name) {
        return skillRepository.findByName(name);
    }

    @Override
    public Skill created(Skill skill) {
        Skill skills = skillRepository.save(skill);
        return skills;
    }

    @Override
    public Skill update(Skill skill) {
        Skill skills = skillRepository.save(skill);
        return skills;
    }

    @Override
    public void deleteById(Integer id) {
        skillRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        skillRepository.deleteAll();
        skillRepositoryCustom.resetAutoIncrement();
    }
}
