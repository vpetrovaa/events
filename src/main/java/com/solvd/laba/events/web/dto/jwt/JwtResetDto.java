package com.solvd.laba.events.web.dto.jwt;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class JwtResetDto {

    @NotEmpty(message = "Email is required")
    private String email;

}
