package com.solvd.laba.events.web.controller;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.Password;
import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.domain.criteria.EventCriteria;
import com.solvd.laba.events.domain.jwt.JwtAuth;
import com.solvd.laba.events.domain.jwt.JwtRefresh;
import com.solvd.laba.events.domain.jwt.JwtResponse;
import com.solvd.laba.events.service.AuthService;
import com.solvd.laba.events.service.EventService;
import com.solvd.laba.events.service.TicketService;
import com.solvd.laba.events.service.UserService;
import com.solvd.laba.events.web.dto.EventDto;
import com.solvd.laba.events.web.dto.PasswordDto;
import com.solvd.laba.events.web.dto.TicketDto;
import com.solvd.laba.events.web.dto.UserDto;
import com.solvd.laba.events.web.dto.criteria.EventCriteriaDto;
import com.solvd.laba.events.web.dto.jwt.JwtAuthDto;
import com.solvd.laba.events.web.dto.jwt.JwtRefreshDto;
import com.solvd.laba.events.web.dto.jwt.JwtResponseDto;
import com.solvd.laba.events.web.mapper.*;
import com.solvd.laba.events.web.mapper.criteria.EventCriteriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TicketService ticketService;
    private final UserMapper userMapper;
    private final TicketMapper ticketMapper;
    private final PasswordMapper passwordMapper;
    private final JwtAuthMapper jwtAuthMapper;
    private final AuthService authService;
    private final JwtResponseMapper jwtResponseMapper;
    private final JwtRefreshMapper jwtRefreshMapper;
    private final EventService eventService;
    private final EventMapper eventMapper;
    private final EventCriteriaMapper eventCriteriaMapper;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtResponseDto login(@RequestBody @Validated JwtAuthDto requestUserDto) {
        JwtAuth requestUser = jwtAuthMapper.toEntity(requestUserDto);
        JwtResponse jwtToken = authService.login(requestUser);
        return jwtResponseMapper.toDto(jwtToken);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtResponseDto refresh(@RequestBody @Validated JwtRefreshDto refreshDto) {
        JwtRefresh refresh = jwtRefreshMapper.toEntity(refreshDto);
        JwtResponse jwtToken = authService.refresh(refresh);
        return jwtResponseMapper.toDto(jwtToken);
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody @Validated UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userService.create(user);
        userDto = userMapper.toDto(user);
        return userDto;
    }

    @PutMapping("/{userId}/password")
    public void changePassword(@PathVariable Long userId,
                               @RequestBody PasswordDto passwordDto) {
        Password password = passwordMapper.toEntity(passwordDto);
        userService.updatePassword(password, userId);
        //TODO methods to change and reset passwords
    }

    @GetMapping("/events")
    public List<EventDto> findAll(@RequestParam(required = false) Integer currentPage,
                                  @RequestBody EventCriteriaDto criteriaDto) {
        EventCriteria criteria = eventCriteriaMapper.toEntity(criteriaDto);
        List<Event> events = eventService.findAll(currentPage, criteria);
        return eventMapper.toDto(events);
    }


    @GetMapping("/events/{eventId}")
    public EventDto findById(@PathVariable Long eventId) {
        Event event = eventService.findById(eventId);
        return eventMapper.toDto(event);
    }


    @GetMapping("/{userId}/tickets")
    public List<TicketDto> getUserTickets(@PathVariable Long userId,
                                          @RequestParam(required = false) LocalDateTime currentDate) {
        List<Ticket> tickets = ticketService.findAll(userId, currentDate);
        return ticketMapper.toDto(tickets);
    }

    @PostMapping("/{userId}/tickets/{eventId}")
    public TicketDto addTicket(@PathVariable Long userId,
                               @PathVariable Long eventId,
                               @RequestParam(required = false) Integer amount) {
        Ticket ticket = ticketService.create(userId, eventId, amount);
        return ticketMapper.toDto(ticket);
    }

    @GetMapping("/tickets/{ticketId}")
    public TicketDto getTicketById(@PathVariable Long ticketId) {
        Ticket ticket = ticketService.findById(ticketId);
        return ticketMapper.toDto(ticket);
    }

    @DeleteMapping("/tickets/{ticketId}")
    public void deleteTicketById(@PathVariable Long ticketId) {
        ticketService.deleteById(ticketId);
    }

}
