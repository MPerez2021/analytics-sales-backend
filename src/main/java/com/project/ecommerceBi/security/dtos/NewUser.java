package com.project.ecommerceBi.security.dtos;

import com.project.ecommerceBi.security.entities.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class NewUser {

    @NotBlank
    @Getter @Setter
    private String userName;

    @Email
    @Getter @Setter
    private String email;

    @NotBlank
    @Getter @Setter
    private String password;

    @Getter @Setter
    private final Set<String> roles = new HashSet<>();


}
