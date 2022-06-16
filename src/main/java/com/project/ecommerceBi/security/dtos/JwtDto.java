package com.project.ecommerceBi.security.dtos;

import lombok.Getter;
import lombok.Setter;

public class JwtDto {

    @Getter @Setter
    private String token;

    public JwtDto() {
    }

    public JwtDto(String token) {
        this.token = token;
    }
}
