package com.solvd.laba.events.web.dto.jwt;

import lombok.Data;

@Data
public class JwtResponse {

    private final String accessToken;
    private final String refreshToken;

}
