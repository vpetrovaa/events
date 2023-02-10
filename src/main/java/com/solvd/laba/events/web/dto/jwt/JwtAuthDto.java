package com.solvd.laba.events.web.dto.jwt;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class JwtAuthDto {

    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

}
