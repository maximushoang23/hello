package com.example.vssemployee.repository;

import com.example.vssemployee.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 9:55 AM
 */
public interface LevelRepository extends JpaRepository<Level, Integer> {
    List<Level> findByName(@Param(value = "name") String name);
}
