package com.example.vssemployee.service;

import com.example.vssemployee.entity.Level;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 9:53 AM
 */
public interface ILevelService {
    List<Level> getAlls();

    Level getById(Integer id);

    List<Level> getByName(String name);

    Level create(Level level);

    Level update(Level level);

    void deleteById(Integer id);

    void deleteAlls();
}
