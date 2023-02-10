package com.solvd.laba.events.web.dto.jwt;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class JwtRefreshDto {

    @NotEmpty(message = "Refresh token is required")
    private String refreshToken;

}
