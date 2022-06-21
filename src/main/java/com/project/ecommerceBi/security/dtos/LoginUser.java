package com.project.ecommerceBi.security.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class LoginUser {

    @NotBlank
    @Getter @Setter
    private String email;

    @NotBlank
    @Getter @Setter
    private String password;
}
