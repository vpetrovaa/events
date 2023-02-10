package com.solvd.laba.events.web.controller;

import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.service.AuthService;
import com.solvd.laba.events.service.UserService;
import com.solvd.laba.events.web.dto.UserDto;
import com.solvd.laba.events.web.dto.jwt.JwtAuthDto;
import com.solvd.laba.events.web.dto.jwt.JwtRefreshDto;
import com.solvd.laba.events.web.dto.jwt.JwtResetDto;
import com.solvd.laba.events.web.dto.jwt.JwtResponse;
import com.solvd.laba.events.web.dto.validation.OnCreate;
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

    @PostMapping("/register")
    public void register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setRole(User.Role.ROLE_USER);
        userService.create(user);
    }

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtAuthDto authDto) {
        return authService.login(authDto.getEmail(), authDto.getPassword());
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@Validated @RequestBody JwtRefreshDto refreshDto) {
        return authService.refresh(refreshDto.getRefreshToken());
    }

    @PostMapping("/forget")
    public void forget(@Validated @RequestBody JwtResetDto resetDto) {
        authService.sendRestoreToken(resetDto.getEmail());
    }

    @PostMapping("/password/reset")
    public void reset(@RequestParam String token,
                      @RequestBody String password) {
        authService.resetPassword(token, password);
    }

}
