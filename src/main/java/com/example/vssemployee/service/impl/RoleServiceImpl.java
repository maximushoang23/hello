package com.example.vssemployee.service.impl;

import com.example.vssemployee.entity.Role;
import com.example.vssemployee.repository.RoleRepository;
import com.example.vssemployee.service.IRoleService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author manhdt14
 * created in 8/31/2021 9:18 AM
 */
@Service
public class RoleServiceImpl implements IRoleService {
    private final static Logger logger = Logger.getLogger(RoleServiceImpl.class);
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role created(Role role) {
        Role roles = roleRepository.save(role);
        return roles;
    }

    @Override
    public Role update(Role role) {
        Role roles = roleRepository.save(role);
        return roles;
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }
}
