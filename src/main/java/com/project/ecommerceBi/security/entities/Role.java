package com.project.ecommerceBi.security.entities;

import com.project.ecommerceBi.security.enums.RoleName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @NotNull
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Role() {
    }

    public Role(@NotNull RoleName roleName) {
        this.roleName = roleName;
    }
}
