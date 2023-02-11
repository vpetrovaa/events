package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.jwt.JwtAuth;
import com.solvd.laba.events.domain.jwt.JwtRefresh;
import com.solvd.laba.events.domain.jwt.JwtReset;
import com.solvd.laba.events.web.dto.jwt.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtAuth auth);

    JwtResponse refresh(JwtRefresh refresh);

    void sendRestoreToken(JwtReset reset);

    void resetPassword(String token, String password);

}
