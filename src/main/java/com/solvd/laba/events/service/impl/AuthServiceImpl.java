package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.domain.jwt.JwtAuth;
import com.solvd.laba.events.domain.jwt.JwtRefresh;
import com.solvd.laba.events.domain.jwt.JwtReset;
import com.solvd.laba.events.service.AuthService;
import com.solvd.laba.events.web.dto.jwt.JwtResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public JwtResponse login(JwtAuth auth) {
        return null;
    }

    @Override
    public JwtResponse refresh(JwtRefresh refresh) {
        return null;
    }

    @Override
    public void sendRestoreToken(JwtReset reset) {

    }

    @Override
    public void resetPassword(String token, String password) {

    }

}
