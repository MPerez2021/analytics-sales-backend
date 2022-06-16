package com.project.ecommerceBi.security.repositories;

import com.project.ecommerceBi.security.entities.Role;
import com.project.ecommerceBi.security.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}
