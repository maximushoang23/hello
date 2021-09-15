package com.example.vssemployee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author manhdt14
 * created in 8/31/2021 9:51 AM
 */
@Entity
@Data
@Table(name = "m_level")
@AllArgsConstructor
@NoArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
