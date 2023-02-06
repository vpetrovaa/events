package com.solvd.laba.events.web.controller;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.service.EventService;
import com.solvd.laba.events.service.UserService;
import com.solvd.laba.events.web.dto.EventDto;
import com.solvd.laba.events.web.dto.UserDto;
import com.solvd.laba.events.web.mapper.EventMapper;
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
    private final EventService eventService;
    private final EventMapper eventMapper;

    @PostMapping("/users/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto userDto) {
        User user = userMapper.dtoToEntity(userDto);
        user = userService.create(user);
        return userMapper.entityToDto(user);
    }

    @PatchMapping("users/{id}/passwords/reset/{newPassword}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto resetPassword(@PathVariable(name = "id") Long id, @PathVariable(name = "newPassword") String newPassword) {
        User user = userService.resetPassword(newPassword, id);
        return userMapper.entityToDto(user);
    }

    @PatchMapping("users/{id}/passwords/update/{newPassword}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updatePassword(@PathVariable(name = "id") Long id, @PathVariable(name = "newPassword") String newPassword,
                                  @RequestParam("oldPassword") String oldPassword) {
        User user = userService.updatePassword(newPassword, oldPassword, id);
        return userMapper.entityToDto(user);
    }

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto create(@RequestBody EventDto eventDto) {
        Event event = eventMapper.dtoToEntity(eventDto);
        event = eventService.create(event);
        return eventMapper.entityToDto(event);
    }

    @PatchMapping("events/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventDto updateStatus(@PathVariable(name = "id") Long id, @RequestParam("status") String status) {
        Event event = eventService.updateStatus(status, id);
        return eventMapper.entityToDto(event);
    }

}
