package com.project.ecommerceBi.security.services;


import com.project.ecommerceBi.security.entities.Role;
import com.project.ecommerceBi.security.enums.RoleName;
import com.project.ecommerceBi.security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> getByRoleName(RoleName rolName){
        return roleRepository.findByRoleName(rolName);
    }


    public void save(Role role) {
        roleRepository.save(role);
    }
}
