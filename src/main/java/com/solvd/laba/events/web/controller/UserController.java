package com.solvd.laba.events.web.controller;

import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.service.UserService;
import com.solvd.laba.events.web.dto.UserDto;
import com.solvd.laba.events.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto userDto) {
        User user = userMapper.dtoToEntity(userDto);
        user = userService.create(user);
        return userMapper.entityToDto(user);
    }

    @PatchMapping("/{id}/passwords/reset/{newPassword}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto resetPassword(@PathVariable(name = "id") Long id, @PathVariable(name = "newPassword") String newPassword) {
        User user = userService.resetPassword(newPassword, id);
        return userMapper.entityToDto(user);
    }

    @PutMapping(path = "/{id}/passwords/update/{newPassword}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updatePassword(@PathVariable(name = "id") Long id, @PathVariable(name = "newPassword") String newPassword,
                                  @RequestParam("oldPassword") String oldPassword) {
        User user = userService.updatePassword(newPassword, oldPassword, id);
        return userMapper.entityToDto(user);
    }

}
