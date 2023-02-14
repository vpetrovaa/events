package com.solvd.laba.events.domain.jwt;

import lombok.Data;

@Data
public class JwtResponse {

    private String accessToken;
    private String refreshToken;
    private Long expiration;

}
