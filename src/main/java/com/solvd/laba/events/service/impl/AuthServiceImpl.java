package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.domain.exception.AuthenticationException;
import com.solvd.laba.events.domain.jwt.JwtAuth;
import com.solvd.laba.events.domain.jwt.JwtRefresh;
import com.solvd.laba.events.domain.jwt.JwtReset;
import com.solvd.laba.events.domain.jwt.JwtResponse;
import com.solvd.laba.events.service.AuthService;
import com.solvd.laba.events.service.JwtService;
import com.solvd.laba.events.service.UserService;
import com.solvd.laba.events.web.security.JwtUser;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;

    @Override
    public JwtResponse login(JwtAuth requestDto) {
        final User user = userService.findByEmail(requestDto.getEmail());
        if (!user.isActivated()) {
            throw new AuthenticationException("User with id " + user.getId() + " is not activated");
        }
        if (!bCryptPasswordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Wrong password");
        }
        return jwtService.generateJwtToken(user);
    }

    @Override
    public void sendResetToken(JwtReset reset) {
        User user = userService.findByEmail(reset.getEmail());
        String resetToken = jwtService.generateResetToken(user);
        //TODO send this token to email
    }

    @Override
    public void resetPassword(String token, String password) {
        JwtUser jwtUser = jwtService.extractAllClaims(token);
        User user = userService.findByEmail(jwtUser.getEmail());
        userService.resetPassword(password, user.getId());
    }

    @Override
    public JwtResponse refresh(JwtRefresh refresh) {
        String refreshToken = refresh.getRefreshToken();
        JwtUser jwtUser = jwtService.extractAllClaims(refreshToken);
        User user = userService.findByEmail(jwtUser.getEmail());
        JwtResponse newToken = new JwtResponse();
        String newAccessToken = jwtService.generateAccessToken(user);
        newToken.setAccessToken(newAccessToken);
        newToken.setRefreshToken(jwtService.generateRefreshToken(user));
        newToken.setExpiration(jwtService.extractClaim(newAccessToken, Claims::getExpiration).getTime());
        return newToken;
    }

}

