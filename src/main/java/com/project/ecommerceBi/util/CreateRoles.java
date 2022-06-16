package com.project.ecommerceBi.util;

import com.project.ecommerceBi.security.entities.Role;
import com.project.ecommerceBi.security.enums.RoleName;
import com.project.ecommerceBi.security.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception{
        Role rolAdmin = new Role(RoleName.ROLE_ADMIN);
        Role rolClient = new Role(RoleName.ROLE_CLIENT);
        roleService.save(rolAdmin);
        roleService.save(rolClient);
    }
}
 */