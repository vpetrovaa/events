package com.solvd.laba.events.web.controller;

import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.service.UserService;
import com.solvd.laba.events.web.dto.UserDto;
import com.solvd.laba.events.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AppController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto userDto) {
        User user = userMapper.dtoToEntity(userDto);
        user = userService.create(user);
        userDto = userMapper.entityToDto(user);
        return userDto;
    }

}
