package com.solvd.laba.events.service;

import com.solvd.laba.events.web.dto.jwt.JwtResponse;

public interface AuthService {

    JwtResponse login(String email, String password);

    JwtResponse refresh(String refreshToken);

    void sendRestoreToken(String email);

    void resetPassword(String token, String password);

}
