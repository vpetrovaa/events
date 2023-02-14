package com.solvd.laba.events.web.dto.jwt;

import lombok.Data;

@Data
public class JwtResponseDto {

    private String accessToken;
    private String refreshToken;
    private Long expiration;

}
