package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.domain.jwt.JwtResponse;
import com.solvd.laba.events.web.security.JwtUser;
import io.jsonwebtoken.Claims;

import java.util.function.Function;

public interface JwtService {

    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    JwtUser extractAllClaims(String token);

    JwtResponse generateJwtToken(User user);

}
