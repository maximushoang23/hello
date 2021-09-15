package com.example.vssemployee.service;

import com.example.vssemployee.entity.Role;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 9:17 AM
 */

public interface IRoleService {
    List<Role> getAllRoles();

    Role getRoleById(Integer id);

    List<Role> getRoleByName(String name);

    Role created(Role role);

    Role update(Role role);

    void deleteById(Integer id);

    void deleteAll();
}
