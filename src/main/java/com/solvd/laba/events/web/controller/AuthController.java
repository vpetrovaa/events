package com.solvd.laba.events.web.controller;

import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.domain.jwt.JwtAuth;
import com.solvd.laba.events.domain.jwt.JwtRefresh;
import com.solvd.laba.events.domain.jwt.JwtReset;
import com.solvd.laba.events.service.AuthService;
import com.solvd.laba.events.service.UserService;
import com.solvd.laba.events.web.dto.UserDto;
import com.solvd.laba.events.web.dto.jwt.JwtAuthDto;
import com.solvd.laba.events.web.dto.jwt.JwtRefreshDto;
import com.solvd.laba.events.web.dto.jwt.JwtResetDto;
import com.solvd.laba.events.web.dto.jwt.JwtResponse;
import com.solvd.laba.events.web.dto.validation.OnCreate;
import com.solvd.laba.events.web.mapper.JwtAuthMapper;
import com.solvd.laba.events.web.mapper.JwtRefreshMapper;
import com.solvd.laba.events.web.mapper.JwtResetMapper;
import com.solvd.laba.events.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtAuthMapper jwtAuthMapper;
    private final JwtRefreshMapper jwtRefreshMapper;
    private final JwtResetMapper jwtResetMapper;

    @PostMapping("/register")
    public void register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setRole(User.Role.ROLE_USER);
        userService.create(user);
    }

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtAuthDto authDto) {
        JwtAuth auth = jwtAuthMapper.toEntity(authDto);
        return authService.login(auth);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@Validated @RequestBody JwtRefreshDto refreshDto) {
        JwtRefresh refresh = jwtRefreshMapper.toEntity(refreshDto);
        return authService.refresh(refresh);
    }

    @PostMapping("/forget")
    public void forget(@Validated @RequestBody JwtResetDto resetDto) {
        JwtReset reset = jwtResetMapper.toEntity(resetDto);
        authService.sendRestoreToken(reset);
    }

    @PostMapping("/password/reset")
    public void reset(@RequestParam String token,
                      @RequestBody String password) {
        authService.resetPassword(token, password);
    }

}
