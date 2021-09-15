package com.example.vssemployee.service.impl;

import com.example.vssemployee.entity.Level;
import com.example.vssemployee.repository.LevelRepository;
import com.example.vssemployee.service.ILevelService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 9:54 AM
 */

@Service
public class LevelServiceImpl implements ILevelService {
    private final static Logger logger = Logger.getLogger(LevelServiceImpl.class);
    private LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public List<Level> getAlls() {
        return levelRepository.findAll();
    }

    @Override
    public Level getById(Integer id) {
        return levelRepository.findById(id).orElse(null);
    }

    @Override
    public List<Level> getByName(String name) {
        return levelRepository.findByName(name);
    }

    @Override
    public Level create(Level level) {
        Level levels = levelRepository.save(level);
        return levels;
    }

    @Override
    public Level update(Level level) {
        Level levels = levelRepository.save(level);
        return levels;
    }

    @Override
    public void deleteById(Integer id) {
        levelRepository.deleteById(id);
    }

    @Override
    public void deleteAlls() {
        levelRepository.deleteAll();
    }
}
