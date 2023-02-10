package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.service.AuthService;
import com.solvd.laba.events.web.dto.jwt.JwtResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public JwtResponse login(String email, String password) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }

    @Override
    public void sendRestoreToken(String email) {

    }

    @Override
    public void resetPassword(String token, String password) {

    }

}
