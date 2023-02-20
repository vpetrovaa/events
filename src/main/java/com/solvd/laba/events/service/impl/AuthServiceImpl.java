package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.domain.exception.AuthenticationException;
import com.solvd.laba.events.domain.jwt.JwtAuth;
import com.solvd.laba.events.domain.jwt.JwtRefresh;
import com.solvd.laba.events.domain.jwt.JwtReset;
import com.solvd.laba.events.domain.jwt.JwtResponse;
import com.solvd.laba.events.service.AuthService;
import com.solvd.laba.events.service.EmailService;
import com.solvd.laba.events.service.JwtService;
import com.solvd.laba.events.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

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
        Map<String, Object> params = new HashMap<>();
        params.put("token", resetToken);
        emailService.sendResetTokenEmail(user, params);
    }

    @Override
    public void resetPassword(String token, String password) {
        String email = jwtService.extractClaim(token, Claims::getSubject);
        User user = userService.findByEmail(email);
        userService.resetPassword(password, user.getId());
    }

    @Override
    public JwtResponse refresh(JwtRefresh refresh) {
        String refreshToken = refresh.getRefreshToken();
        String email = jwtService.extractClaim(refreshToken, Claims::getSubject);
        User user = userService.findByEmail(email);
        JwtResponse newToken = new JwtResponse();
        String newAccessToken = jwtService.generateAccessToken(user);
        newToken.setAccessToken(newAccessToken);
        newToken.setRefreshToken(jwtService.generateRefreshToken(user));
        newToken.setExpiration(jwtService.extractClaim(newAccessToken, Claims::getExpiration).getTime());
        return newToken;
    }

}

